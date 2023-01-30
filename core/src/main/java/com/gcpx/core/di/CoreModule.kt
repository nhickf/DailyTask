package com.gcpx.core.di

import com.gcpx.core.data.repository.ILocalSourceRepository
import com.gcpx.core.data.repository.LocalSourceRepositoryImpl
import com.gcpx.core.data.source.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {

    single {
        AppDatabase.getDatabase(androidApplication())
    }

    single<ILocalSourceRepository>{
        LocalSourceRepositoryImpl(
            taskDao = get<AppDatabase>().taskDao()
        )
    }


}