package com.gcpcx.taskdetail.data

import com.gcpx.core.data.model.Task
import com.gcpx.core.data.model.wrapEntityToTask
import com.gcpx.core.data.model.wrapTaskToEntity
import com.gcpx.core.data.repository.ILocalSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskDetailRepositoryImpl(
   private val repository: ILocalSourceRepository
) : ITaskDetailRepository{

    override suspend fun updateTime(id: Int, currentLength: Long) {
        repository.updateTime(id, currentLength)
    }

    override fun getTask(id: Int): Flow<Task> = repository.getTask(id).map {
        it.wrapEntityToTask()
    }
}