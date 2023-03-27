package com.vanilla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {
    private lateinit var composeView: ComposeView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        composeView = ComposeView(requireNotNull(context)).apply {
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

        return composeView
    }
}