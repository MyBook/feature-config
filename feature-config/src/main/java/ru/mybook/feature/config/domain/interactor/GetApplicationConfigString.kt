package ru.mybook.feature.config.domain.interactor

import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.NoValueException

class GetApplicationConfigString(
    private val applicationConfig: ApplicationConfig
) {

    operator fun invoke(propertyName: String, defaultValue: String): String =
        try {
            applicationConfig.getString(propertyName)
        } catch (e: NoValueException) {
            defaultValue
        }

}
