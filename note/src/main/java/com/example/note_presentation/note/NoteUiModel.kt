package com.example.note_presentation.note

import java.time.LocalDateTime

data class NoteUiModel(
    val title: String,
    val content: String,
    val label: String,
    val alarm: LocalDateTime?=null,
    val id: String? =null
)
