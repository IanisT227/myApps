package com.jetbrains.greeting

actual class BatteryManager {
    actual fun getBatteryLevel(): Int = 5
}