package com.example.jetpackcomposeapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComposable(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember {
            mutableStateOf("")
        }

        var isPasswordVisible by remember {
            mutableStateOf(false)
        }

        val focusRequester = remember {
            FocusRequester()
        }

        TextField(
            value = text,
            onValueChange = { text = it },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.key),
                    contentDescription = null
                )
            },
            trailingIcon = {
                TextButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Text(text = if (isPasswordVisible) "Hide" else "Show")
                }
            },
            colors = TextFieldDefaults.colors(

            ),
            singleLine = true,
//            placeholder = {
//                Text(text = "Your password")
//            },
            label = {
                Text(text = "Your password")

            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onGo = {
                    Log.d("Tag", "TextFieldComposable: $text")
                }
            ),
            modifier = Modifier.focusRequester(focusRequester)
        )

        Button(onClick = { focusRequester.requestFocus() }) {
            Text(text = "Request Focus")
        }
    }
}