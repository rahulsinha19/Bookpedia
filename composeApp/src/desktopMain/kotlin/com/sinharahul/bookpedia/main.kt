package com.sinharahul.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sinharahul.bookpedia.app.App
import com.sinharahul.bookpedia.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Bookpedia",
        ) {
            App()
        }
    }
}
