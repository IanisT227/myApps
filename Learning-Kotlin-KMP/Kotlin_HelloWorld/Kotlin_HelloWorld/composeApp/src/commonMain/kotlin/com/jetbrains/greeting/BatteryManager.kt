package com.jetbrains.greeting

expect class BatteryManager {
    fun getBatteryLevel(): Int
}