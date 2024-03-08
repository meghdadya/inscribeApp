package com.example.domain.usecases.note

import com.example.domain.models.DomainModel
import com.example.domain.models.NoteDomainModel
import com.example.domain.repositories.NoteRepository
import com.example.domain.usecases.BaseUseCase
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) :
    BaseUseCase<NoteDomainModel, DomainModel>() {
    override suspend fun execute(param: NoteDomainModel): DomainModel {
        noteRepository.addNote(param)
        return object : DomainModel {}
    }
}