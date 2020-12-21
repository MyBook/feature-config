package ru.mybook.feature.config.sample.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.mybook.feature.config.sample.MyBookApplication

object Koin {
    fun initKoinGraph(application: MyBookApplication) =
        startKoin {

            androidContext(application)

            modules(
                ApplicationConfigModule,
                FeatureConfigModule,
                MyFeatureModule,
            )
        }
}