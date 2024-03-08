package com.example.domain.usecases.note

import com.example.domain.models.ListDomainModel
import com.example.domain.models.NoteDomainModel
import com.example.domain.repositories.NoteRepository
import com.example.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val noteRepository: NoteRepository) :
    BaseUseCase<Unit, ListDomainModel<NoteDomainModel>>() {
    override suspend fun execute(param: Unit): ListDomainModel<NoteDomainModel> =
        noteRepository.getNotes()

}