package ru.mybook.feature.config.sample

import android.app.Application
import ru.mybook.feature.config.sample.di.Koin

class MyBookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Koin.initKoinGraph(this)
    }
}
