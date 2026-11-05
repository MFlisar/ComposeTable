package com.michaelflisar.demo

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() {
    application {
        val windowState = rememberWindowState(
            width = 1024.dp,
            height = 800.dp,
            // ~ center auf 2tem Monitor
            //position = WindowPosition(
            //    2372.dp,
            //    100.dp
            //),

        )
        Window(
            onCloseRequest = ::exitApplication,
            title = "Compose Styled Components Demo",
            state = windowState
        ) {
            LaunchedEffect(Unit) {
                window.toFront()
                window.requestFocus()
            }
            DemoContent()

        }
    }
}