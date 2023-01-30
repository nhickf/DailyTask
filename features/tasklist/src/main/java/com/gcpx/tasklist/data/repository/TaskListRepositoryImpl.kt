package com.gcpx.tasklist.data.repository

import com.gcpx.core.data.repository.ILocalSourceRepository
import com.gcpx.tasklist.data.model.Task
import com.gcpx.tasklist.data.model.wrapEntityToTask
import com.gcpx.tasklist.data.model.wrapTaskToEntity
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

    override suspend fun addTask(task: Task) {
        localSource.addTask(task.wrapTaskToEntity())
    }

    override suspend fun updateTask(task: Task) {
        localSource.updateTask(task.wrapTaskToEntity())
    }

    override suspend fun deleteTask(task: Task) {
        localSource.deleteTask(task.wrapTaskToEntity())
    }
}