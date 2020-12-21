package ru.mybook.feature.config.sample.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import ru.mybook.feature.config.sample.R
import ru.mybook.feature.config.sample.domain.interactor.IsNewsPhotoEnabled
import ru.mybook.feature.config.sample.domain.interactor.NewsPhotoShape

class SamplesActivity : AppCompatActivity() {

    private val isNewsPhotoEnabled: IsNewsPhotoEnabled by inject()
    private val newsPhotoShape: NewsPhotoShape by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_samples)

        findViewById<TextView>(R.id.newsPhotoEnabledValue).text =
            "IsNewsPhotoEnabled: ${isNewsPhotoEnabled()}"

        findViewById<TextView>(R.id.newsPhotoShapeValue).text =
            "NewsPhotoShape: ${newsPhotoShape()}"

    }
}
