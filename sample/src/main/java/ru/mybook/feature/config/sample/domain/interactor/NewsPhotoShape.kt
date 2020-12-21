package ru.mybook.feature.config.sample.domain.interactor

import ru.mybook.feature.config.domain.interactor.GetApplicationConfigString

class NewsPhotoShape(
    private val getApplicationConfigString: GetApplicationConfigString
) {

    operator fun invoke(): String =
        getApplicationConfigString("news_photo_shape", "rectangle")

}
