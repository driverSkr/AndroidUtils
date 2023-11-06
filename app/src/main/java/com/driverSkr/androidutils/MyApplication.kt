package com.driverSkr.androidutils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @Author: driverSkr
 * @Time: 2023/11/6 11:32
 * @Description: 自定义Application，在这里进行全局的初始化操作
 */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}