package com.grpcx.configuretask.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcpx.core.data.model.Task
import com.grpcx.configuretask.data.repository.IConfigureTaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


internal class ConfigureTaskViewModel(
    taskId: Int,
    private val repository: IConfigureTaskRepository
) : ViewModel() {

    private val _uiState : MutableStateFlow<ConfigureTaskUiState> = MutableStateFlow(ConfigureTaskUiState.Loading)
    val uiState : StateFlow<ConfigureTaskUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.emit(
                ConfigureTaskUiState.AddNewTask(
                    Task(
                        title = "",
                        length = 0,
                        currentLength = 0,
                        theme = ""
                    )
                )
            )
        }
    }

    fun saveTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task = task)
        }
    }

    fun onDurationValueChange(value : String){
        viewModelScope.launch {
            with(_uiState.value){
                if (this is ConfigureTaskUiState.AddNewTask){
                    _uiState.emit(ConfigureTaskUiState.AddNewTask(
                        task?.copy(length = value.toInt())
                    ))
                }
            }
        }
    }

    fun onTitleValueChange(value : String){
        viewModelScope.launch {
            with(_uiState.value){
                if (this is ConfigureTaskUiState.AddNewTask){
                    _uiState.emit(ConfigureTaskUiState.AddNewTask(
                        task?.copy(title = value)
                    ))
                }
            }
        }
    }

}

sealed interface ConfigureTaskUiState {
    object Loading : ConfigureTaskUiState
    data class AddNewTask(val task: Task?) : ConfigureTaskUiState
    data class Error(val message: String?) : ConfigureTaskUiState
}
