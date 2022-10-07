package com.example.contactsapp.app

import android.app.Application
import com.example.contactsapp.di.appModule
import com.example.contactsapp.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule))
        }
    }
}