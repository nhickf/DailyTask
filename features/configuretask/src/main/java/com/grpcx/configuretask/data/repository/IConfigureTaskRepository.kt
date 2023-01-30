package com.grpcx.configuretask.data.repository

import com.gcpx.core.data.model.Task
import kotlinx.coroutines.flow.Flow

interface IConfigureTaskRepository {

    fun getTask(id : Int) : Flow<Task>

    suspend fun addTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

}
