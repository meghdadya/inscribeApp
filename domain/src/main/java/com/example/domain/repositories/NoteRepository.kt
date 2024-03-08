package com.example.domain.repositories

import com.example.domain.models.ListDomainModel
import com.example.domain.models.NoteDomainModel

interface NoteRepository {

    suspend fun getNotes(): ListDomainModel<NoteDomainModel>

    suspend fun getNote(id: Int): NoteDomainModel

    suspend fun addNote(note: NoteDomainModel)
    suspend fun updateNote(note: NoteDomainModel)

    suspend fun removeNote(note: NoteDomainModel)

}