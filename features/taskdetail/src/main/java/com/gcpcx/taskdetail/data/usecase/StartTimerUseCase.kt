package com.gcpcx.taskdetail.data.usecase

import android.util.Log
import androidx.work.WorkManager
import com.gcpx.core.data.model.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal class StartTimerUseCase(
    private val workManager: WorkManager
) {

    suspend operator fun invoke(length : Int): Flow<Long> {

        var convertedLengthToMinutes = length.toDuration(DurationUnit.MINUTES)

        return flow {
            while (convertedLengthToMinutes > Duration.ZERO) {
                emit(convertedLengthToMinutes.inWholeMilliseconds)
                convertedLengthToMinutes -= 1.toDuration(DurationUnit.SECONDS)
                delay(1.toDuration(DurationUnit.SECONDS))
            }

        }
    }
}