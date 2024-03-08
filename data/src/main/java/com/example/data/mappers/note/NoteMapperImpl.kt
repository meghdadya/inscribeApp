package com.example.data.mappers.note


import com.example.data.models.Note
import com.example.domain.models.NoteDomainModel
import javax.inject.Inject

class NoteMapperImpl @Inject constructor(): NoteMapper {
    override fun mapToDomainModel(note: Note): NoteDomainModel {
        return with(note) {
            NoteDomainModel(
                title = title,
                content = content,
                alarm = reminderDateTime,
                isReminded = isReminded,
                id = id,
            )
        }
    }

    override fun mapToDataModel(domainModel: NoteDomainModel): Note {
        if (domainModel.id!=null){
            return with(domainModel) {
                Note(
                    title = title,
                    content = content,
                    reminderDateTime = alarm,
                    isReminded = isReminded,
                    id = id!!
                )
            }
        }else{
            return with(domainModel) {
                Note(
                    title = title,
                    content = content,
                    reminderDateTime = alarm,
                    isReminded = isReminded,
                )
            }
        }

    }
}