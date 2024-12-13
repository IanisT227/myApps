package com.example.jetpackcomposeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ButtonComposable() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        SimpleButton()
        CustomTextButton()
    }
}

@Composable
fun SimpleButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(50.dp)
            .width(140.dp),
        shape = RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp),
        enabled = true,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Blue,
            containerColor = Color.Cyan,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        Text(text = "Click me")
    }
}

@Composable
fun CustomTextButton(modifier: Modifier = Modifier) {
    TextButton(onClick = { /*TODO*/ }) {
        Text(text = "Click me")
    }
}