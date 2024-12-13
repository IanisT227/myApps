package com.example.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

class MainActivity : ComponentActivity() {
    val viewmodel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeAppTheme {

//               TextComposableLesson()
//                StateAndViewModelLesson()
//                PostCardLessonComposable()
//                ShowButtonComposable()
                ShowTextFieldComposable()
            }
        }
    }


    @Composable
    fun StateAndViewModelLesson() {
        val state = viewmodel.state.value
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

    @Composable
    fun TextComposableLesson() {
        val textLesson = TextLesson()
        textLesson.TextComposable()

    }

    @Composable
    fun PostCardLessonComposable() {
        val postCardLesson = PostCardLesson()
        val publisher = Publisher(
            name = "Ianis Teja",
            image = R.drawable.ic_launcher_foreground,
            job = "Android Developer"
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        )
        {
            postCardLesson.MyCustomCard(
                drawableRes = R.drawable.monkey,
                title = "Monkeyyy",
                text = "Monkey is a common name that may refer to most mammals of the infraorder Simiiformes, also known as simians. Traditionally, all animals in the group now known as simians are counted as monkeys except the apes. ",
                publisher = publisher,
                modifier = Modifier.padding(20.dp)
            )
        }
    }

    @Composable
    fun ShowButtonComposable() {
    ButtonComposable()
    }

    @Composable
    fun ShowTextFieldComposable(modifier: Modifier = Modifier) {
        TextFieldComposable()
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
}