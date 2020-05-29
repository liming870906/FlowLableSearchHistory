package com.lm.flowlablesearchhistory

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private lateinit var application: MyApplication
        val context: Context
            get() = application.applicationContext
    }
}