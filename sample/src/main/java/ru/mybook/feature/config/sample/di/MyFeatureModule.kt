package ru.mybook.feature.config.sample.di

import org.koin.dsl.module
import ru.mybook.feature.config.domain.interactor.GetApplicationConfigBoolean
import ru.mybook.feature.config.domain.interactor.GetApplicationConfigString
import ru.mybook.feature.config.sample.domain.interactor.IsNewsPhotoEnabled
import ru.mybook.feature.config.sample.domain.interactor.NewsPhotoShape

val MyFeatureModule = module {
    factory<IsNewsPhotoEnabled> {
        val getApplicationConfigBoolean = get<GetApplicationConfigBoolean>()
        IsNewsPhotoEnabled(getApplicationConfigBoolean)
    }

    factory<NewsPhotoShape> {
        val getApplicationConfigString = get<GetApplicationConfigString>()
        NewsPhotoShape(getApplicationConfigString)
    }
}
