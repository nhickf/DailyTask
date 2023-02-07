package com.gcpx.core.data.model

import com.gcpx.core.data.source.database.entities.TaskEntity
import kotlin.time.Duration

data class Task(
    val id: Int = 0,
    val title: String,
    val length: Int,
    val currentLength : Long,
    val theme: String,
    val pause : Boolean = false,
    val done : Boolean = false,
)

fun TaskEntity.wrapEntityToTask(): Task =
    Task(
        id = id,
        title = title,
        length = length,
        theme = theme,
        currentLength = currentLength,
        pause = pause,
        done = done
    )

fun Task.wrapTaskToEntity(): TaskEntity =
    TaskEntity(
        id = id,
        title = title,
        currentLength = currentLength,
        length = length,
        theme = theme,
        pause = pause,
        done = done
    )

fun Task.updateCurrentLength(duration: Duration) = Task(
    id = id,
    title = title,
    currentLength = duration.inWholeMilliseconds,
    length = length,
    theme = theme
)

val defaultTask = Task(
    1,
    "Test",
    123,
    123,
    "",
)
