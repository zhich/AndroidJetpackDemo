package com.zch.demo.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

/**
 * Created by zch on 2020-04-08.
 */
class ProgressWorker(context: Context, parameters: WorkerParameters) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        val firstUpdate = workDataOf("Progress" to 0)
        val lastUpdate = workDataOf("Progress" to 100)
        setProgress(firstUpdate)
        delay(1)
        setProgress(lastUpdate)
        return Result.success()
    }
}