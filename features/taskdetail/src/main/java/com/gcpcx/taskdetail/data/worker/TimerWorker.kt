package com.gcpcx.taskdetail.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class TimerWorker(appContext : Context, workerParam : WorkerParameters) : CoroutineWorker(appContext,workerParam) {

    override suspend fun doWork(): Result {
        val currentLength = inputData.getLong(LENGTH_KEY,1)
        val convertedLengthToMinutes = currentLength.toDuration(DurationUnit.MINUTES)

        while (convertedLengthToMinutes > Duration.ZERO && convertedLengthToMinutes != Duration.ZERO){
            setProgressAsync(workDataOf(PROGRESS_KEY to convertedLengthToMinutes.minus(1.toDuration(DurationUnit.SECONDS))))
        }

       return Result.success()
    }
}

const val PROGRESS_KEY = "RUNNING"
const val LENGTH_KEY = "LENGTH"
const val TIMER_TAG = "TIMER_TAG"