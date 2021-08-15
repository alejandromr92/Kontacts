package com.alejandromr.kontacts

import android.app.Application
import com.alejandromr.kontacts.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KontactsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KontactsApplication)
            modules(appModules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}
