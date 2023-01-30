package com.gcpx.core.data.source.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gcpx.core.data.source.database.dao.TaskDao
import com.gcpx.core.data.source.database.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object{
        @Volatile
        private var INSTANCE  : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room
                    .databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "task")
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }

}
