package ru.mybook.feature.config.sample.domain.interactor

import ru.mybook.feature.config.domain.interactor.GetApplicationConfigBoolean

class IsNewsPhotoEnabled(
    private val getApplicationConfigBoolean: GetApplicationConfigBoolean
) {

    operator fun invoke(): Boolean =
        getApplicationConfigBoolean("news_photo", false)

}
