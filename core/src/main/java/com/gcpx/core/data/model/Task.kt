package com.gcpx.core.data.model

import com.gcpx.core.data.source.database.entities.TaskEntity

data class Task(
    val id: Int = 0,
    val title: String,
    val length: Int,
    val theme: String
)

fun TaskEntity.wrapEntityToTask(): Task =
    Task(
        id = id,
        title = title,
        length = length,
        theme = theme
    )

fun Task.wrapTaskToEntity(): TaskEntity =
    TaskEntity(
        id = id,
        title = title,
        length = length,
        theme = theme
    )

val defaultTask = Task(
    1,
    "Test",
    123,
    ""
)
