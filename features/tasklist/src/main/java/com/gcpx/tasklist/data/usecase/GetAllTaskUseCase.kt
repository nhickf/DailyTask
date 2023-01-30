package com.gcpx.tasklist.data.usecase

import com.gcpx.tasklist.data.model.Task
import com.gcpx.tasklist.data.repository.ITaskListRepository
import kotlinx.coroutines.flow.Flow

internal class GetAllTaskUseCase(
    private val repository : ITaskListRepository
) {

    operator fun invoke() : Flow<List<Task>> = repository.getAll()

}