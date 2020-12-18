package ru.mybook.feature.config.domain.interactor

class IsFeatureEnabled(
    val getApplicationConfigBoolean: GetApplicationConfigBoolean
) {

    operator fun invoke(
        featureName: String,
        defaultValue: Boolean = false
    ): Boolean =
        getApplicationConfigBoolean("${featureName}_enabled", defaultValue)

}
