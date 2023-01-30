package com.grpcx.configuretask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gcpx.core.data.model.Task
import com.gcpx.core.data.model.defaultTask
import com.grpcx.configuretask.domain.ConfigureTaskUiState
import com.grpcx.configuretask.domain.ConfigureTaskViewModel
import org.koin.androidx.compose.koinViewModel
import com.gcpcx.core.R

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ConfigureTaskScope(defaultTask){}
}


@Composable
fun ConfigureTaskScreen() {
    val viewModel = koinViewModel<ConfigureTaskViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    
    ConfigureTaskContent(state = uiState)
}

@Composable
fun ConfigureTaskContent(state : ConfigureTaskUiState){
    when(state){
        is ConfigureTaskUiState.Error -> TODO()
        ConfigureTaskUiState.Loading -> TODO()
        ConfigureTaskUiState.AddNewTask -> ConfigureTaskScope(task = null, onClick = {

        })
        is ConfigureTaskUiState.UpdateTask-> ConfigureTaskScope(task = state.tasks, onClick = {

        })
    }
}

@Composable
fun ConfigureTaskScope(task : Task?, onClick:(task : Task) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = task?.title.toString(),
            placeholder = {
                Text(text = "Task Title")
            },
            onValueChange = {},
        )

        OutlinedTextField(
            value = task?.length.toString(),
            placeholder = {
                          Text(text = "Task Duration in Minutes")
            },
            onValueChange = {},
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_clock), contentDescription = null)
            }
        )

        ThemeField()

        Button(onClick = {
            onClick(task ?: Task(1, "2", 3, "33"))
        }) {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ThemeField() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .border(1.dp, color = Color.Gray, shape = MaterialTheme.shapes.small),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_clock),
            contentDescription = null,
            tint = Color.Gray
        )

        Text(
            text = "Theme"
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.Blue)
                .border(width = 1.dp, color = Color.Gray),
        ){}

    }
}
