package com.gcpx.core.data.repository

import com.gcpx.core.data.source.database.dao.TaskDao
import com.gcpx.core.data.source.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

internal class LocalSourceRepositoryImpl(
   private val taskDao: TaskDao
) : ILocalSourceRepository {
    override fun getAll(): Flow<List<TaskEntity>> {
        return taskDao.getAll()
    }

    override fun getTask(id: Int): Flow<TaskEntity> {
        return taskDao.getTask(id)
    }

    override suspend fun updateTime(id: Int, currentLength: Long) {
        return taskDao.updateTime(id, currentLength)
    }

    override suspend fun addTask(taskEntity: TaskEntity) {
        taskDao.addTask(taskEntity)
    }

    override suspend fun updateTask(taskEntity: TaskEntity) {
        taskDao.updateTask(taskEntity)
    }

    override suspend fun deleteTask(taskEntity: TaskEntity) {
        taskDao.deleteTask(taskEntity)
    }
}