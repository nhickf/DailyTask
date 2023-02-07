package com.gcpcx.taskdetail.data.usecase

import com.gcpcx.taskdetail.data.ITaskDetailRepository
import com.gcpx.core.data.model.Task

internal class PauseTimerUseCase(
    val repository: ITaskDetailRepository
) {

    suspend operator fun invoke(task : Task) {}
}