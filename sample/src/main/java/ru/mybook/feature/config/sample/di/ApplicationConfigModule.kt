package ru.mybook.feature.config.sample.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.module
import ru.mybook.feature.config.BuildConfig
import ru.mybook.feature.config.CompositeApplicationConfig
import ru.mybook.feature.config.UserApplicationConfig
import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.sample.DebugApplicationConfig

val ApplicationConfigModule = module {

    single<SharedPreferences> {
        val context = get<Context>()
        context.getSharedPreferences("mybook", Context.MODE_PRIVATE)
    }

    single<ApplicationConfig>(createdAtStart = true) {

        val configs = mutableListOf<ApplicationConfig>()

        configs += get<UserApplicationConfig>()

        if (BuildConfig.DEBUG) {
            configs += DebugApplicationConfig()
        }

        CompositeApplicationConfig(*configs.toTypedArray())
    }

}
