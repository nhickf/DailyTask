package com.gcpx.tasklist.data.usecase

import com.gcpx.core.data.model.Task
import com.gcpx.tasklist.data.repository.ITaskListRepository

internal class AddTaskUseCase(
    val repository: ITaskListRepository
) {

    suspend operator fun invoke(task: Task) {
    }

}
