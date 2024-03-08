package com.example.data.di

import com.example.data.mappers.note.NoteMapper
import com.example.data.mappers.note.NoteMapperImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Binds


@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindNoteMapper(
        noteRepositoryImpl: NoteMapperImpl,
    ): NoteMapper

}