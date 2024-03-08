package com.example.data.data_source.local.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.data_source.local.database.NoteDB
import com.example.data.models.Note
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class NoteDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var noteDao: NoteDao
    private lateinit var noteDatabase: NoteDB

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        noteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDB::class.java
        ).allowMainThreadQueries().build()
        noteDao = noteDatabase.noteDao
    }

    @After
    fun tearDown() {
        noteDatabase.close()
    }

    @Test
    fun testInsertAndGetNotes() = runBlocking {
        val note = Note(id = 1, title = "Test Note", content = "This is a test note")
        noteDao.insertNote(note)
        val notes = noteDao.getNotes()
        assert(notes.contains(note))
    }

    @Test
    fun testUpdateNote() = runBlocking {
        val note = Note(id = 1, title = "Test Note", content = "This is a test note")
        noteDao.insertNote(note)
        val updatedNote = note.copy(title = "Updated Note")
        noteDao.updateNote(updatedNote)

        val retrievedNote = noteDao.getNoteById(1)
        assert(retrievedNote.title == "Updated Note")
    }


}
