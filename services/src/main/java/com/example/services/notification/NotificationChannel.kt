package com.example.services.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color


fun createNotificationChannel(
    context: Context,
) {
    val notificationManager = context.getSystemService(NotificationManager::class.java)
    NotificationChannel("1", "ReminderNote", NotificationManager.IMPORTANCE_HIGH).apply {
        description = "Reminder Notes"
        enableLights(true)
        lightColor = Color.BLUE
        enableVibration(true)
        notificationManager.createNotificationChannel(this)
    }
}
