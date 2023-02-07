package com.gcpx.core.data.repository

import com.gcpx.core.data.source.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface ILocalSourceRepository {

    fun getAll() : Flow<List<TaskEntity>>

    fun getTask(id : Int) : Flow<TaskEntity>

    suspend fun updateTime(id: Int , currentLength : Long)

    suspend fun addTask(taskEntity: TaskEntity)

    suspend fun updateTask(taskEntity: TaskEntity)

    suspend fun deleteTask(taskEntity: TaskEntity)
}