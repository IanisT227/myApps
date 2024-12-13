package com.example.jetpackcomposeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

class TextLesson {

    @Composable
    fun TextComposable() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val annotatedString = buildAnnotatedString {
                BlueGradientText(text = "This is a")
                append("\n")
                RedBlueGradientText(text = "Gradient Text")
            }
            
            Text(text = annotatedString)
        }
    }

    @Composable
    private fun AnnotatedString.Builder.BlueGradientText(text: String) {
        withStyle(
            style = SpanStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Blue,
                        Color.Red
                    )
                ),
                fontSize = 42.sp
            )
        ) {
            append(text)
        }
    }

    @Composable
    private fun AnnotatedString.Builder.RedBlueGradientText(text: String) {
        withStyle(
            style = SpanStyle(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Red,
                        Color.Blue
                    )
                ),
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
            )
        ) {
            append(text)
        }
    }
}