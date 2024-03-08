package com.example.data.data_source.local.note

import com.example.data.models.Note


interface NoteDataSource {

    suspend fun getNotes() : List<Note>

    suspend fun getNoteById(id:Int) : Note
    suspend fun getNoteByRowId(rowId:Int) : Note?

    suspend fun insertNote(note: Note):Long
    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}