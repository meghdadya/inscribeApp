package com.example.data.repository

import com.example.data.data_source.local.note.NoteDataSource
import com.example.data.mappers.note.NoteMapper
import com.example.data.mappers.note.NoteMapperImpl
import com.example.data.utils.triggerDateTime
import com.example.domain.models.ListDomainModel
import com.example.domain.models.NoteDomainModel
import com.example.domain.repositories.NoteRepository
import com.example.services.alarm.AlarmInfo
import com.example.services.alarm.AlarmScheduler
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val noteMapper: NoteMapper,
    private val alarmScheduler: AlarmScheduler
) : NoteRepository {
    override suspend fun getNotes(): ListDomainModel<NoteDomainModel> =
        ListDomainModel(item = noteDataSource.getNotes().map {
            noteMapper.mapToDomainModel(it)
        })

    override suspend fun getNote(id: Int): NoteDomainModel =
        noteMapper.mapToDomainModel(noteDataSource.getNoteById(id))


    override suspend fun addNote(note: NoteDomainModel) {
        val rowId = noteDataSource.insertNote(noteMapper.mapToDataModel(note))
        val note = noteDataSource.getNoteByRowId(rowId.toInt())
        note?.reminderDateTime?.let {
            alarmScheduler.scheduleAlarm(
                alarmInfo = AlarmInfo(
                    noteId = note.id,
                    triggerMillis = it.triggerDateTime()
                )
            )
        }

    }
    override suspend fun updateNote(note: NoteDomainModel) {
        noteDataSource.updateNote(noteMapper.mapToDataModel(note))
        note.alarm?.let {
            alarmScheduler.editScheduleAlarm(
                alarmInfo = AlarmInfo(
                    noteId = note.id!!,
                    triggerMillis = it.triggerDateTime()
                )
            )
        }
    }

    override suspend fun removeNote(note: NoteDomainModel) =
        noteDataSource.deleteNote(noteMapper.mapToDataModel(note))
}