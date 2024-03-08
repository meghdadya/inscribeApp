package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Note(
    val title: String,
    val content: String,
    var reminderDateTime: LocalDateTime? = null,
    var isReminded: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int =0
) : LocalDataModel