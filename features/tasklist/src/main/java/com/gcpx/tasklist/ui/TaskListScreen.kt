package com.gcpx.tasklist.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.gcpx.tasklist.data.model.Task
import com.gcpx.tasklist.domain.TaskListViewModel
import com.gcpx.tasklist.domain.TaskUiState
import com.gcpx.tasklist.ui.components.TaskItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen() {

    val viewModel = koinViewModel<TaskListViewModel>()
    val state by viewModel.uiState.collectAsState()

    TaskListContent(state = state)
}

@Composable
private fun TaskListContent(
    state : TaskUiState
) {
    when(state){
        TaskUiState.Empty -> EmptyItems()
        TaskUiState.Loading -> LoadingItems()
        is TaskUiState.Tasks -> TaskListScope(items = state.tasks)
    }
}

@Composable
private fun TaskListScope(
    items : List<Task>
) {
    LazyColumn{
        items(items){
            TaskItem(task = it)
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
