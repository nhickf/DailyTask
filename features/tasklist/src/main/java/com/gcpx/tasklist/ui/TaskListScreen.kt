package com.gcpx.tasklist.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gcpx.core.data.model.Task
import com.gcpx.core.ui.AppToolbar
import com.gcpx.tasklist.domain.TaskListViewModel
import com.gcpx.tasklist.domain.TaskUiState
import com.gcpx.tasklist.ui.components.TaskItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
    navigateToConfigureTask: () -> Unit, onItemClick: (taskId : Int) -> Unit
) {

    val viewModel = koinViewModel<TaskListViewModel>()
    val state by viewModel.uiState.collectAsState()

    TaskListContent(
        state = state,
        navigateToConfigureTask = navigateToConfigureTask,
        onItemClick = onItemClick
    )
}

@Composable
private fun TaskListContent(
    state: TaskUiState,
    navigateToConfigureTask: () -> Unit,
    onItemClick: (taskId : Int) -> Unit
) {

    Column {
        AppToolbar(
            trailingIcon = Icons.Default.Add,
            trailingIconOnClick = navigateToConfigureTask
        )
        when (state) {
            TaskUiState.Empty -> EmptyItems()
            TaskUiState.Loading -> LoadingItems()
            is TaskUiState.Tasks -> TaskListScope(items = state.tasks) {
                onItemClick.invoke(it)
            }
        }
    }
}

@Composable
private fun TaskListScope(
    items: List<Task>,
    onItemClick: (taskId : Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal =
            16.dp
        )
    )
    {

        item {
            Text(
                text = "Daily Tasks",
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(items) {
            TaskItem(task = it) { id ->
                onItemClick.invoke(id)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun LoadingItems() {
    Text(text = "Loading items")
}

@Composable
fun EmptyItems() {
    Text(text = "No items")
}
