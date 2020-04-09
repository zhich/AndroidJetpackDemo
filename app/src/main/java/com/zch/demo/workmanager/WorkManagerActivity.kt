package com.zch.demo.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.zch.demo.R
import com.zch.demo.utils.LogUtil
import com.zch.demo.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_workmanager.*
import java.util.concurrent.TimeUnit

/**
 * Created by zch on 2020-04-07.
 */
class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workmanager)

        btnSleepWorker?.setOnClickListener {
            testSleepWorker()
        }

        btnUploadWorker?.setOnClickListener {
            testUploadLogWorker()
        }

        btnProgressWorker?.setOnClickListener {
            testProgressWorker()
        }

        btnTaskChainWorker?.setOnClickListener {
            testTaskChainWorker()
        }

        btnTaskChainWorker2?.setOnClickListener {
            testTaskChainWorker2()
        }
    }

    private fun testSleepWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // 连网
                .setRequiresCharging(true) // 充电中
                .setRequiresBatteryNotLow(true) // 电量不能太低
                .build()
        val sleepRequest = OneTimeWorkRequest.Builder(SleepWorker::class.java)
                .setConstraints(constraints) // 设置触发条件
                .build()

        WorkManager.getInstance(applicationContext).enqueue(sleepRequest)
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(sleepRequest.id).observe(this, Observer { workInfo ->
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                ToastUtil.showShortText("SleepWorker")
            }
        })
    }

    private fun testUploadLogWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val inputData = workDataOf("name" to "张三", "id" to 112134)
        val uploadLogRequest = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
                .setConstraints(constraints)
                .setInitialDelay(5, TimeUnit.SECONDS) // 符合触发条件后，延迟 5 秒执行
                .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS) // 设置退避策略
                .setInputData(inputData)
                .build()

        WorkManager.getInstance(applicationContext).enqueue(uploadLogRequest)
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(uploadLogRequest.id).observe(this, Observer { workInfo ->
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                ToastUtil.showShortText("UploadLogWorker")
                val name = workInfo.outputData.getString("name")
                val id = workInfo.outputData.getInt("id", 0)
                LogUtil.d("name-->$name, id-->$id")
            }
        })
    }

    private fun testProgressWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val progressRequest = OneTimeWorkRequest.Builder(ProgressWorker::class.java)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(progressRequest)
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(progressRequest.id).observe(this, Observer { workInfo ->
            if (workInfo != null) {
                ToastUtil.showShortText("ProgressWorker")
                val progress = workInfo.progress
                val value = progress.getInt("Progress", 0)
                LogUtil.d("progress-->$value")
            }
        })
    }

    private fun testTaskChainWorker() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val sleepWorkerRequest = OneTimeWorkRequest.Builder(SleepWorker::class.java)
                .setConstraints(constraints)
                .build()
        val myWorkerRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
                .setConstraints(constraints)
                .build()
        val myWorker2Request = OneTimeWorkRequest.Builder(MyWorker2::class.java)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(applicationContext)
                .beginWith(listOf(sleepWorkerRequest, myWorkerRequest)) // 并发
                .then(myWorker2Request) // 串发
                .enqueue()
    }

    private fun testTaskChainWorker2() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val A = OneTimeWorkRequest.Builder(SleepWorker::class.java)
                .setConstraints(constraints)
                .build()
        val B = OneTimeWorkRequest.Builder(MyWorker::class.java)
                .setConstraints(constraints)
                .build()
        val C = OneTimeWorkRequest.Builder(SleepWorker::class.java)
                .setConstraints(constraints)
                .build()
        val D = OneTimeWorkRequest.Builder(MyWorker::class.java)
                .setConstraints(constraints)
                .build()
        val E = OneTimeWorkRequest.Builder(MyWorker2::class.java)
                .setConstraints(constraints)
                .build()
        val chuan1 = WorkManager.getInstance(applicationContext)
                .beginWith(A)
                .then(B)
        val chuan2 = WorkManager.getInstance(applicationContext)
                .beginWith(C)
                .then(D)

        WorkContinuation.combine(listOf(chuan1, chuan2))
                .then(E)
                .enqueue()
    }
}