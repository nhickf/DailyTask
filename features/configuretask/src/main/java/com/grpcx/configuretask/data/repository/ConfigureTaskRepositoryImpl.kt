package com.grpcx.configuretask.data.repository

import com.gcpx.core.data.model.Task
import com.gcpx.core.data.model.wrapEntityToTask
import com.gcpx.core.data.model.wrapTaskToEntity
import com.gcpx.core.data.repository.ILocalSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConfigureTaskRepositoryImpl(
    private val repository: ILocalSourceRepository
) : IConfigureTaskRepository{
    override fun getTask(id: Int): Flow<Task> = repository.getTask(id).map {
        it.wrapEntityToTask()
    }

    override suspend fun addTask(task: Task) {
        repository.addTask(taskEntity = task.wrapTaskToEntity())
    }

    override suspend fun updateTask(task: Task) {
        repository.updateTask(taskEntity = task.wrapTaskToEntity())
    }

    override suspend fun deleteTask(task: Task) {
        repository.deleteTask(taskEntity = task.wrapTaskToEntity())
    }

}
