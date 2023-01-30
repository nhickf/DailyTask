package com.gcpx.tasklist.data.repository

import com.gcpx.core.data.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskListRepository {

    fun getAll() : Flow<List<Task>>

    fun getTask(id : Int) : Flow<Task>

    suspend fun updateTask(task: Task)

}
