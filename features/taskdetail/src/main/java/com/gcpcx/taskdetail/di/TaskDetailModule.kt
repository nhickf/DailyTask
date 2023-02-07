package com.gcpcx.taskdetail.di

import androidx.work.WorkManager
import com.gcpcx.taskdetail.data.ITaskDetailRepository
import com.gcpcx.taskdetail.data.TaskDetailRepositoryImpl
import com.gcpcx.taskdetail.data.usecase.StartTimerUseCase
import com.gcpcx.taskdetail.domain.TaskDetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val taskDetailModule = module {

    single<ITaskDetailRepository> {
        TaskDetailRepositoryImpl(repository = get())
    }

    single {
        StartTimerUseCase(
            workManager = get()
        )
    }

    single { WorkManager.getInstance(androidApplication()) }

    viewModel {
        TaskDetailViewModel(
            taskId = get(),
            workManager = get(),
            repository = get(),
            startTimerUseCase = get()
        )
    }
}