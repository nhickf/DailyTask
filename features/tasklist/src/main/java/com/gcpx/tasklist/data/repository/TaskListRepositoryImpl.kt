package com.gcpx.tasklist.data.repository

import com.gcpx.core.data.model.Task
import com.gcpx.core.data.model.wrapEntityToTask
import com.gcpx.core.data.model.wrapTaskToEntity
import com.gcpx.core.data.repository.ILocalSourceRepository

import kotlinx.coroutines.flow.*

class TaskListRepositoryImpl(
    val localSource: ILocalSourceRepository
) : ITaskListRepository {
    override fun getAll(): Flow<List<Task>> = localSource.getAll().map { tasks ->
        tasks.map { it.wrapEntityToTask() }
    }

    override fun getTask(id: Int): Flow<Task> = localSource.getTask(id).map {
        it.wrapEntityToTask()
    }

    override suspend fun updateTask(task: Task) {
        localSource.updateTask(task.wrapTaskToEntity())
    }

}
