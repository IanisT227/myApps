package com.example.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

class MainActivity : ComponentActivity() {
     val viewmodel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = viewmodel.state.value
            JetpackComposeAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(state.nameList.size) {
                            Text(text = state.nameList[it])
                        }
                    }

                    MyTextField(textValue = state.textValue, onValueChanged = {
                        viewmodel.updateTextValue(it)
                    }, onAddClick = {
                        viewmodel.updateNamesList()
                        viewmodel.updateTextValue("")
                    })
                }
            }
        }
    }
}

@Composable
fun MyTextField(
    textValue: String, onValueChanged: (String) -> Unit, onAddClick: () -> Unit
) {
    TextField(value = textValue, onValueChange = {
        onValueChanged(it)
    }, modifier = Modifier.fillMaxWidth(), trailingIcon = {
        Icon(imageVector = Icons.Default.Add,
            contentDescription = "",
            modifier = Modifier.clickable { onAddClick() })
    })
}
