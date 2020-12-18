package ru.mybook.feature.config.domain

interface ApplicationConfig {

    fun hasFeature(key: String): Boolean

    fun getString(key: String): String

    fun getBoolean(key: String): Boolean

    fun getLong(key: String): Long

}
