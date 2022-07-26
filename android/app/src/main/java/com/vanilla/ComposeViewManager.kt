package com.vanilla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

class ComposeViewManager : SimpleViewManager<ComposeView>() {

    override fun getName() = "ComposeView"

    override fun createViewInstance(context: ThemedReactContext): ComposeView {
        return ComposeView(context).apply {
            setBackgroundColor(android.graphics.Color.GREEN)
            setContent {
                Text(
                    text = "ComposeView content",
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .background(color = Color.Magenta)
                )
            }
        }
    }
}
