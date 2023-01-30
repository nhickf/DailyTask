package com.grpcx.configuretask.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcpx.core.data.model.Task
import com.grpcx.configuretask.data.repository.IConfigureTaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


internal class ConfigureTaskViewModel(
    private val taskId : Int,
    private val repository: IConfigureTaskRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow<ConfigureTaskUiState>(ConfigureTaskUiState.Loading)
    val uiState : StateFlow<ConfigureTaskUiState> = repository.getTask(taskId)
        .map { ConfigureTaskUiState.AddNewTask(it) }
        .onEmpty { ConfigureTaskUiState.AddNewTask(null) }
        .catch { ConfigureTaskUiState.Error(it.message) }
        .stateIn(
            scope = viewModelScope,
            initialValue = ConfigureTaskUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )

    init {
    }


    fun saveTask(task: Task){
        viewModelScope.launch {
            repository.addTask(task = task)
        }
    }

}

sealed interface ConfigureTaskUiState {
    object Loading : ConfigureTaskUiState
    data class AddNewTask(val task: Task?) : ConfigureTaskUiState
    data class Error(val message : String?) : ConfigureTaskUiState
}
