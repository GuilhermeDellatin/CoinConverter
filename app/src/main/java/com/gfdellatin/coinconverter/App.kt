package com.gfdellatin.coinconverter

import android.app.Application
import com.gfdellatin.coinconverter.data.di.DataModules
import com.gfdellatin.coinconverter.domain.di.DomainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModules.load()
        DomainModules.load()
    }
}