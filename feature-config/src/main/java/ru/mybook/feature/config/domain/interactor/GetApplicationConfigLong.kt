package ru.mybook.feature.config.domain.interactor

import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.NoValueException

class GetApplicationConfigLong(
    private val applicationConfig: ApplicationConfig
) {

    operator fun invoke(propertyName: String, defaultValue: Long): Long =
        try {
            applicationConfig.getLong(propertyName)
        } catch (e: NoValueException) {
            defaultValue
        }

}
