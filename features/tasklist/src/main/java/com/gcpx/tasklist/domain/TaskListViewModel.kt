package com.gcpx.tasklist.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcpx.core.data.model.Task
import com.gcpx.tasklist.data.usecase.TaskUseCase
import kotlinx.coroutines.flow.*

internal class TaskListViewModel(
    useCase: TaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = useCase.getAllTaskUseCase()
        .map {
           return@map if (it.isEmpty()) {
                TaskUiState.Empty
            } else {
                TaskUiState.Tasks(it)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TaskUiState.Loading
        )
}

sealed interface TaskUiState {
    object Loading : TaskUiState
    data class Tasks(val tasks: List<Task>) : TaskUiState
    object Empty : TaskUiState
}
