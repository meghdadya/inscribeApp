package com.example.services.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.domain.models.NoteDomainModel
import com.example.core.R


fun NotificationManager.sendReminderNote(context: Context, note: NoteDomainModel) {
    val notification = NotificationCompat.Builder(context, "1").apply {
        setSmallIcon(R.drawable.clock)
        setContentTitle(note.title)
        setContentText(note.content)
        setAutoCancel(true)
    }.build()
    notify(note.id ?: 0, notification)
}