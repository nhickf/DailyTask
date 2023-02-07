package com.gcpcx.taskdetail.domain

import android.util.Log
import android.util.TimeUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.gcpcx.taskdetail.data.ITaskDetailRepository
import com.gcpcx.taskdetail.data.Timer
import com.gcpcx.taskdetail.data.usecase.StartTimerUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal class TaskDetailViewModel(
    private val taskId: Int,
    private val workManager: WorkManager,
    private val startTimerUseCase: StartTimerUseCase,
    private val repository: ITaskDetailRepository
) : ViewModel() {

    val uiState: StateFlow<TaskDetailUiState> = repository.getTask(taskId)
        .map {
            TaskDetailUiState.Success(
                Timer(
                    length = it.length,
                    minutes = (it.currentLength / 1000 / 60).toString(),
                    seconds = (it.currentLength / 1000 % 60).toString(),
                    elapsedMinutes =  "",
                    remainingMinutes = ""
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = TaskDetailUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )


    fun startTimer(length: Int) {
        viewModelScope.launch {
            startTimerUseCase.invoke(length)
                .onEach {time ->
                    Log.e("Progress",time.toString())
                    repository.updateTime(
                        taskId,time
                    )
                }.collect()
        }
    }

    fun pauseTimer() {
    }

    fun resetTimer() {
    }
}

sealed interface TaskDetailUiState {
    object Loading : TaskDetailUiState
    data class Success(val timer: Timer) : TaskDetailUiState
}