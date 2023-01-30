package com.gcpcx.dailytask

import android.app.Application
import com.gcpx.core.di.coreModule
import com.gcpx.tasklist.di.taskListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    coreModule,
                    taskListModule
                )
            )
        }
    }
}