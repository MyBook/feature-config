package ru.mybook.feature.config.presentation

import android.content.Intent
import android.net.Uri
import ru.mybook.feature.config.UserApplicationConfig

class ConsumeReceivedLink(
    private val userApplicationConfig: UserApplicationConfig
) {

    operator fun invoke(intent: Intent) {
        checkAction(intent)
        val uri = getData(intent)
        handleUriParameters(uri.queryParameters)
    }

    private fun checkAction(intent: Intent) {
        val action = intent.action
        if (action != REQUIRED_ACTION) {
            throw IllegalArgumentException(
                "This activity must be executed with action [$REQUIRED_ACTION] but was [$action]"
            )
        }
    }

    private fun getData(intent: Intent): Uri =
        intent.data ?: throw IllegalArgumentException("No data passed to activity intent")

    private fun handleUriParameters(parameters: Map<String, String?>) =
        parameters.forEach { (parameterName, queryParameterValue) ->
            handleParameter(parameterName, queryParameterValue)
        }

    private fun handleParameter(name: String, rawValue: String?) {

        if (rawValue.isNullOrBlank()) {
            userApplicationConfig.removeByKey(name)
            return
        }

        val type = rawValue.substringBefore('_')
        val stringValue = rawValue.removePrefix("${type}_")

        userApplicationConfig.apply {
            when (type) {
                "string" -> setStringValue(name, stringValue)
                "boolean" -> setBooleanValue(name, stringValue.toBoolean())
                "int" -> setIntValue(name, stringValue.toInt())
                "long" -> setLongValue(name, stringValue.toLong())
                else -> throw IllegalArgumentException("Unsupported value type [$type]")
            }
        }

    }

    private val Uri.queryParameters: Map<String, String?>
        get() = queryParameterNames.associateWith { getQueryParameter(it) }

    companion object {
        const val REQUIRED_ACTION = "android.intent.action.VIEW"
    }

}
