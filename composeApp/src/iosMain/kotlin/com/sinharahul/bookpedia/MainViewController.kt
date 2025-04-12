package com.sinharahul.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.sinharahul.bookpedia.app.App
import com.sinharahul.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }
