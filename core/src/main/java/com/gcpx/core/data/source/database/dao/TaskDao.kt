package com.gcpx.core.data.source.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gcpx.core.data.source.database.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TaskDao {

    @Query("Select * from TaskEntity order by length DESC")
    fun getAll() : Flow<List<TaskEntity>>

    @Query("Select * from TaskEntity where id =:id")
    fun getTask(id : Int) : Flow<TaskEntity>

    @Insert
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}