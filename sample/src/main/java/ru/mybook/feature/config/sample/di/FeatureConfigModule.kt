package ru.mybook.feature.config.sample.di

import android.content.SharedPreferences
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.mybook.feature.config.UserApplicationConfig
import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.interactor.*
import ru.mybook.feature.config.presentation.ConsumeReceivedLink

val FeatureConfigModule = module {

    factory<GetApplicationConfigBoolean> {
        val applicationConfig: ApplicationConfig = get()
        GetApplicationConfigBoolean(applicationConfig)
    }

    factory<GetApplicationConfigLong> {
        val applicationConfig: ApplicationConfig = get()
        GetApplicationConfigLong(applicationConfig)
    }

    factory<GetApplicationConfigString> {
        val applicationConfig: ApplicationConfig = get()
        GetApplicationConfigString(applicationConfig)
    }

    factory<UserApplicationConfig> {
        val preferences: SharedPreferences =
            get<SharedPreferences> { parametersOf("config") }
        UserApplicationConfig(preferences)
    }

    factory<IsFeatureEnabled> {
        val getApplicationConfigBoolean: GetApplicationConfigBoolean =
            get<GetApplicationConfigBoolean>()
        IsFeatureEnabled(getApplicationConfigBoolean)
    }

    factory<WhenFeatureEnabled> {
        val isFeatureEnabled: IsFeatureEnabled = get<IsFeatureEnabled>()
        WhenFeatureEnabled(isFeatureEnabled)
    }

    factory<WhenFeatureDisabled> {
        val isFeatureEnabled: IsFeatureEnabled = get<IsFeatureEnabled>()
        WhenFeatureDisabled(isFeatureEnabled)
    }

    factory<ConsumeReceivedLink> {
        val userApplicationConfig = get<UserApplicationConfig>()
        ConsumeReceivedLink(userApplicationConfig)
    }

}
