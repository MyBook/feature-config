package ru.mybook.feature.config.domain.interactor

class WhenFeatureEnabled(
    val isFeatureEnabled: IsFeatureEnabled
) {

    inline operator fun invoke(
        featureName: String,
        defaultValue: Boolean = false,
        action: () -> Unit
    ) {
        if (isFeatureEnabled(featureName, defaultValue)) {
            action()
        }
    }

}
