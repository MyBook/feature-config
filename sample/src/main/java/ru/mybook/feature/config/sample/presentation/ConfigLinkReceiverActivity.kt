package ru.mybook.feature.config.sample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import ru.mybook.feature.config.presentation.ConsumeReceivedLink

class ConfigLinkReceiverActivity : AppCompatActivity() {

    private val consumeReceivedLink: ConsumeReceivedLink by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        consumeReceivedLink(intent)
        finish()
    }
}