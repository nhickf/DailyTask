package com.gcpx.tasklist.di

import com.gcpx.tasklist.data.repository.ITaskListRepository
import com.gcpx.tasklist.data.repository.TaskListRepositoryImpl
import com.gcpx.tasklist.data.usecase.AddTaskUseCase
import com.gcpx.tasklist.data.usecase.GetAllTaskUseCase
import com.gcpx.tasklist.data.usecase.TaskUseCase
import com.gcpx.tasklist.domain.TaskListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val taskListModule = module {
    single<ITaskListRepository> {
        TaskListRepositoryImpl(localSource = get())
    }

    single {
        GetAllTaskUseCase(repository = get())
    }

    single {
        AddTaskUseCase(repository = get())
    }

    single {
        TaskUseCase(
            getAllTaskUseCase = get(),
            addTaskUseCase = get()
        )
    }

    viewModel {
        TaskListViewModel(useCase = get())
    }
}