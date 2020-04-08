package com.zch.demo.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.zch.demo.utils.LogUtil

/**
 * Created by zch on 2020-04-08.
 */
class UploadLogWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val name = inputData.getString("name")
        val id = inputData.getInt("id", 0)
        LogUtil.d("name-->$name, id--->$id")

        val outputData = workDataOf("name" to name, "id" to id)
        return Result.success(outputData)
    }
}