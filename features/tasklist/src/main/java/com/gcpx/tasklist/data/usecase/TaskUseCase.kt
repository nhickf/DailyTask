package com.gcpx.tasklist.data.usecase

internal data class TaskUseCase(
    val getAllTaskUseCase: GetAllTaskUseCase,
    val addTaskUseCase: AddTaskUseCase
)
