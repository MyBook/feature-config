package ru.mybook.feature.config

import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.NoValueException

class CompositeApplicationConfig(
    private vararg val appConfigs: ApplicationConfig
) : ApplicationConfig {

    override fun hasFeature(key: String): Boolean =
        getValue(key) { hasFeature(key) }

    override fun getString(key: String): String =
        getValue(key) { getString(key) }

    override fun getBoolean(key: String): Boolean =
        getValue(key) { getBoolean(key) }

    override fun getLong(key: String): Long =
        getValue(key) { getLong(key) }

    private inline fun <reified T> getValue(
        key: String,
        crossinline getTypedValue: ApplicationConfig.() -> T
    ): T =
        appConfigs
            .asSequence()
            .map {
                try {
                    it.getTypedValue()
                } catch (e: NoValueException) {
                    null
                }
            }
            .filter { it != null }
            .firstOrNull() ?: throw NoValueException("There is no value for key [$key]")

}
