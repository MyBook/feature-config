package ru.mybook.feature.config

import android.content.SharedPreferences
import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.NoValueException

class UserApplicationConfig(
    private val preferences: SharedPreferences
) : ApplicationConfig {

    override fun hasFeature(key: String): Boolean =
        preferences
            .ifExist(key)
            .contains(key)

    override fun getString(key: String): String =
        preferences
            .ifExist(key)
            .getString(key, null)!!

    override fun getBoolean(key: String): Boolean =
        preferences
            .ifExist(key)
            .getBoolean(key, false)

    override fun getLong(key: String): Long =
        preferences
            .ifExist(key)
            .getLong(key, -1)

    fun setBooleanValue(key: String, value: Boolean) =
        preferences.edit()
            .putBoolean(key, value)
            .apply()

    fun setIntValue(key: String, value: Int) =
        preferences.edit()
            .putInt(key, value)
            .apply()

    fun setLongValue(key: String, value: Long) =
        preferences.edit()
            .putLong(key, value)
            .apply()

    fun setStringValue(key: String, value: String) =
        preferences.edit()
            .putString(key, value)
            .apply()

    fun removeByKey(key: String) =
        preferences.edit()
            .remove(key)
            .apply()

    private fun SharedPreferences.ifExist(key: String): SharedPreferences =
        apply {
            if (!contains(key)) {
                throw NoValueException("Key [$key] not exist in preferences")
            }
        }

}
