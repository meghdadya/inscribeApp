package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.data_source.local.database.NoteDB
import com.example.data.data_source.local.note.NoteDataSource
import com.example.data.data_source.local.note.NoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatasourceModule {

    @Singleton
    @Provides
    fun provideMarketsDatabase(app: Application): NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(db: NoteDB): NoteDataSource {
        return NoteDataSourceImpl(db.noteDao)
    }
}
