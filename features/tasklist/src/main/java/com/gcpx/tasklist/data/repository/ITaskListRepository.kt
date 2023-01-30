package com.gcpx.tasklist.data.repository

import com.gcpx.tasklist.data.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskListRepository {

    fun getAll() : Flow<List<Task>>

    fun getTask(id : Int) : Flow<Task>

    suspend fun addTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

}