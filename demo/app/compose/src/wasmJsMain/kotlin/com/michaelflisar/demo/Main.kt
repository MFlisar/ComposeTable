package com.michaelflisar.demo

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
suspend fun main() {
    val config = IndexConfig()

    ComposeViewport(
        viewportContainerId = config.contentDivId
    ) {
        HideLoading(config.loadingDivId)
        DemoContent()
    }
}

/** ATTENTION:
 * must match the div ids in index.html
 * [demo\app\compose\src\wasmJsMain\resources\index.html]
 */
data class IndexConfig(
    val contentDivId: String = "content",
    val loadingDivId: String = "loading",
)

@Composable
private fun HideLoading(id: String) {
    LaunchedEffect(id) {
        document.getElementById(id)
            ?.setAttribute("style", "display:none")
    }
}