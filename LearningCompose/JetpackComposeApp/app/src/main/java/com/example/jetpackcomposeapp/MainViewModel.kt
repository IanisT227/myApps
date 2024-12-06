package com.example.jetpackcomposeapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val state = mutableStateOf(MyScreenState())

    fun updateTextValue(newValue: String) {
        state.value = state.value.copy(textValue = newValue)
    }

    fun updateNamesList() {
        val currentList = state.value.nameList
        currentList.add(state.value.textValue)
        state.value = state.value.copy(nameList = currentList)
    }
}