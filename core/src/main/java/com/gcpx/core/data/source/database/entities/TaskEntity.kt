package com.gcpx.core.data.source.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int ,
    val title : String,
    val length : Int,
    val theme : String,
)
