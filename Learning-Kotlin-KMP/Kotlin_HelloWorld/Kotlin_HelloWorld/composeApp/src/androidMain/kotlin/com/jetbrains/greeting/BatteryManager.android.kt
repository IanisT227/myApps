package com.jetbrains.greeting

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager.*

actual class BatteryManager(private val context: Context) {
    actual fun getBatteryLevel(): Int {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = context.registerReceiver(null, intentFilter);
        val level = batteryStatus?.getIntExtra(EXTRA_LEVEL, -1) ?: -1
        val scale = batteryStatus?.getIntExtra(EXTRA_SCALE, -1) ?: -1
        return level * 100 / scale
    }
}