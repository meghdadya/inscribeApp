package com.example.data.data_source.local.note

import com.example.data.models.Note
import com.example.data.data_source.local.database.dao.NoteDao
import javax.inject.Inject

class NoteDataSourceImpl @Inject constructor(
    private val dao: NoteDao
) : NoteDataSource {
    override suspend fun getNotes(): List<Note> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note {
        return dao.getNoteById(id)
    }

    override suspend fun getNoteByRowId(rowId: Int): Note? {
        return dao.getNoteByRowId(rowId)
    }

    override suspend fun insertNote(note: Note): Long {
        return dao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
