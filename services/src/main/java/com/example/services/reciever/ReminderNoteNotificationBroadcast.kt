package com.example.services.reciever

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.domain.usecases.note.EditNoteUseCase
import com.example.domain.usecases.note.GetNoteUseCase
import com.example.services.utils.Const
import com.example.services.utils.sendReminderNote
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReminderNoteNotificationBroadcast : BroadcastReceiver() {

    @Inject
    lateinit var notificationManager: NotificationManager

    @Inject
    lateinit var getNoteUseCase: GetNoteUseCase

    @Inject
    lateinit var editNoteUseCase: EditNoteUseCase


    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    override fun onReceive(context: Context?, intent: Intent?) {
        goAsync().apply {
            kotlin.runCatching {
                coroutineScope.launch {
                    val getNoteId = intent!!.getIntExtra(Const.NOTE_ID_EXTRA, -1)
                    if (getNoteId == -1) {
                        throw com.example.services.exception.NoteNotFoundException("There is Error Note not found")
                    }
                    val getNoteById = getNoteUseCase.execute(getNoteId)
                    //  Just to confirm that no errors occurred and send any notification
                    if (!getNoteById.isReminded) {
                        notificationManager.sendReminderNote(context!!, getNoteById)
                        editNoteUseCase.execute(getNoteById.copy(isReminded = true))
                    }
                }
            }.onSuccess {
                it.invokeOnCompletion {
                    // after scope is finish close broadcast receiver
                    finish()
                }
            }.onFailure {
                when (it) {
                    is com.example.services.exception.NoteNotFoundException -> {
                        // NO Op for now
                    }
                    else -> {
                        // NO Op for now
                    }
                }
                finish()
            }
        }
    }
}
