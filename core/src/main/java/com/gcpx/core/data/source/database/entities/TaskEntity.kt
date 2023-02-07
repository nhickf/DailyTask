package com.gcpx.core.data.source.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val currentLength : Long,
    val length: Int,
    val theme: String,
    val pause : Boolean = false,
    val done : Boolean = false,
)
