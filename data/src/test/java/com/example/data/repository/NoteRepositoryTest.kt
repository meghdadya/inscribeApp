package com.example.data.repository

import com.example.domain.models.ListDomainModel
import com.example.domain.models.NoteDomainModel
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NoteRepositoryTest {

    @Mock
    private lateinit var noteRepository: NoteRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetNotes() = runBlocking {
        val expectedNotes =ListDomainModel(item = listOf(
            NoteDomainModel(id = 1, title = "Note 1", content = "Content 1", alarm = null),
            NoteDomainModel(id = 2, title = "Note 2", content = "Content 2", alarm = null)
        ))
        `when`(noteRepository.getNotes()).thenReturn(expectedNotes)

        val actualNotes = noteRepository.getNotes()

        assertEquals(expectedNotes, actualNotes)
    }

    @Test
    fun testGetNote() = runBlocking {
        val expectedNote =
            NoteDomainModel(id = 1, title = "Note 1", content = "Content 1", alarm = null)
        `when`(noteRepository.getNote(1)).thenReturn(expectedNote)

        val actualNote = noteRepository.getNote(1)

        assertEquals(expectedNote, actualNote)
    }

    @Test
    fun testAddNote() = runBlocking {
        val note = NoteDomainModel(id = 1, title = "Note 1", content = "Content 1", alarm = null)
        noteRepository.addNote(note)

        // Add assertions if necessary
    }

    @Test
    fun testUpdateNote() = runBlocking {
        val note = NoteDomainModel(id = 1, title = "Note 1", content = "Content 1", alarm = null)
        noteRepository.updateNote(note)

        // Add assertions if necessary
    }

    @Test
    fun testRemoveNote() = runBlocking {
        val note = NoteDomainModel(id = 1, title = "Note 1", content = "Content 1", alarm = null)
        noteRepository.removeNote(note)

    }
}

