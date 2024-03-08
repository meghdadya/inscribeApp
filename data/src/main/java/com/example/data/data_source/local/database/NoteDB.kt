package com.example.data.data_source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.data_source.local.database.converter.LocalDateTimeConverter
import com.example.data.data_source.local.database.dao.NoteDao
import com.example.data.models.Note

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(
    LocalDateTimeConverter::class
)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "notes_db"
    }
}