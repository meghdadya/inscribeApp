package com.example.services.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
fun getNoteAlarmPendingIntent(
    context: Context,
    id: Int,
    flag: Int = PendingIntent.FLAG_UPDATE_CURRENT,
): PendingIntent? {
    return PendingIntent.getBroadcast(
        context,
        id,
        Intent(context, com.example.services.reciever.ReminderNoteNotificationBroadcast::class.java).apply {
            putExtra(Const.NOTE_ID_EXTRA, id)
        },
        flag or PendingIntent.FLAG_IMMUTABLE
    )
}
