package com.example.jetpackcomposeapp

data class MyScreenState(
    val textValue: String = "",
    val nameList: MutableList<String> = mutableListOf()
)
