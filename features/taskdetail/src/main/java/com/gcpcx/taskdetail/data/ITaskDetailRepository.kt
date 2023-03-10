package com.gcpcx.taskdetail.data

import com.gcpx.core.data.model.Task
import kotlinx.coroutines.flow.Flow

interface ITaskDetailRepository {

    suspend fun updateTime(id: Int , currentLength : Long)

    fun getTask(id : Int) : Flow<Task>

}