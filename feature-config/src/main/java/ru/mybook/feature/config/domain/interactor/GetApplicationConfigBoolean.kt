package ru.mybook.feature.config.domain.interactor

import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.NoValueException

class GetApplicationConfigBoolean(
    private val applicationConfig: ApplicationConfig
) {

    operator fun invoke(propertyName: String, defaultValue: Boolean): Boolean =
        try {
            applicationConfig.getBoolean(propertyName)
        } catch (e: NoValueException) {
            defaultValue
        }

}
