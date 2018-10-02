package com.learn.mock.Base

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        private var appContext: Context? = null

        fun setAppContext(context: Context): Context {
            if (appContext == null) appContext = context
            return appContext!!
        }

        // Do not use inside view class (Activity / Fragment / Adapter)
        fun getAppContext(): Context? {
            return appContext
        }
    }
}
