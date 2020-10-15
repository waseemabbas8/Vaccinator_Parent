package com.childhealthcare.parent

import android.app.Application
import com.childhealthcare.parent.di.repositoryModule
import com.childhealthcare.parent.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Parent: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@Parent)
            // modules
            modules(listOf(viewModelModule, repositoryModule))
        }
    }
}