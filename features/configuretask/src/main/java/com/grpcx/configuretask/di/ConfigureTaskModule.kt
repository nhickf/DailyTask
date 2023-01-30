package com.grpcx.configuretask.di

import com.grpcx.configuretask.data.repository.ConfigureTaskRepositoryImpl
import com.grpcx.configuretask.data.repository.IConfigureTaskRepository
import com.grpcx.configuretask.domain.ConfigureTaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val configureTaskModule = module {


    single<IConfigureTaskRepository> {
        ConfigureTaskRepositoryImpl(repository = get())
    }

    viewModel {
        ConfigureTaskViewModel(
            taskId = get(),
            repository = get()
        )
    }
}
