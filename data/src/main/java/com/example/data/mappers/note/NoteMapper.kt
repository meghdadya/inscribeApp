package com.example.data.mappers.note

import com.example.data.models.Note
import com.example.domain.models.NoteDomainModel

interface NoteMapper {
    fun mapToDomainModel(dataModel: Note): NoteDomainModel
    fun mapToDataModel(domainModel: NoteDomainModel): Note
}