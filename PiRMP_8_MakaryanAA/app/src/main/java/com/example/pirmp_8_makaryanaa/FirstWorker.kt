package com.example.pirmp_8_makaryanaa

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class FirstWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result{
        Log.d("Workers", "Первая задача запущена")
        Thread.sleep(3000)
        Log.d("Workers", "Первая задача выполнена")
        return Result.success()
    }

}