package ru.mybook.feature.config.sample

import ru.mybook.feature.config.BuildConfig
import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.NoValueException
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class DebugApplicationConfig : ApplicationConfig {

    private val filePath = "data/local/tmp"

    private val fileName =
        "ru.mybook" + (if (BuildConfig.DEBUG) ".debug" else "") + ".config"

    private val file = File(filePath, fileName)

    override fun hasFeature(key: String): Boolean =
        properties.getString(key).isNotEmpty()

    override fun getString(key: String): String =
        properties.getString(key)

    override fun getBoolean(key: String): Boolean =
        properties.getBoolean(key)

    override fun getLong(key: String): Long =
        properties.getLong(key)

    private fun Properties.getString(key: String): String =
        getProperty(key)
            ?: throw NoValueException("No value for key [$key]")

    private fun Properties.getBoolean(key: String): Boolean =
        when (val value = getString(key)) {
            "true", "false" -> value.toBoolean()
            else -> throw NoValueException("There is no value for key [$key]")
        }

    private fun Properties.getLong(key: String): Long =
        getString(key).toLong()

    private val properties: Properties
        get() {
            if (!file.exists()) {
                throw NoValueException(
                    "Config file [${file.absolutePath}] not exist."
                )
            }
            return Properties()
                .apply {
                    FileInputStream(file)
                        .use(this::load)
                }
        }

}
