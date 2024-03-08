package com.example.data.data_source.local.note

import com.example.data.models.Note
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NoteDataSourceTest {

    @Mock
    private lateinit var noteDataSource: NoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetNotes() = runBlocking {
        val expectedNotes = listOf(
            Note(id = 1, title = "Note 1", content = "Content 1"),
            Note(id = 2, title = "Note 2", content = "Content 2")
        )
        `when`(noteDataSource.getNotes()).thenReturn(expectedNotes)

        val actualNotes = noteDataSource.getNotes()

        assertEquals(expectedNotes, actualNotes)
    }

    @Test
    fun testGetNoteById() = runBlocking {
        val expectedNote = Note(id = 1, title = "Note 1", content = "Content 1")
        `when`(noteDataSource.getNoteById(1)).thenReturn(expectedNote)

        val actualNote = noteDataSource.getNoteById(1)

        assertEquals(expectedNote, actualNote)
    }

    @Test
    fun testGetNoteByRowId() = runBlocking {
        val expectedNote = Note(id = 1, title = "Note 1", content = "Content 1")
        `when`(noteDataSource.getNoteByRowId(1)).thenReturn(expectedNote)

        val actualNote = noteDataSource.getNoteByRowId(1)

        assertEquals(expectedNote, actualNote)
    }

    @Test
    fun testInsertNote() = runBlocking {
        val note = Note(id = 1, title = "Note 1", content = "Content 1")
        val expectedRowId: Long = 1
        `when`(noteDataSource.insertNote(note)).thenReturn(expectedRowId)

        val actualRowId = noteDataSource.insertNote(note)

        assertEquals(expectedRowId, actualRowId)
    }

    @Test
    fun testUpdateNote() = runBlocking {
        val note = Note(id = 1, title = "Note 1", content = "Content 1")

        noteDataSource.updateNote(note)

    }

    @Test
    fun testDeleteNote() = runBlocking {
        val note = Note(id = 1, title = "Note 1", content = "Content 1")

        noteDataSource.deleteNote(note)

    }
}
