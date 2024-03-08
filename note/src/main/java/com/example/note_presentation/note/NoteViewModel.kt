package com.example.note_presentation.note


import androidx.lifecycle.viewModelScope
import com.example.core_ui.base.BaseViewModel
import com.example.domain.usecases.note.GetNotesUseCase

import com.example.note_presentation.note.NoteContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
) : BaseViewModel<NoteViewEvent, NoteState, NoteViewEffect>() {
    override fun createInitialState(): NoteState {
        return NoteState(NoteViewState.Loading)
    }

    override fun handleEvent(event: NoteViewEvent) {
        when (event) {
            NoteViewEvent.GetNotes -> getNotes()
        }
    }

    private fun getNotes() {
        viewModelScope.launch {
            val r = getNotesUseCase.execute(Unit).item.map {
                NoteUiModel(
                    title = it.title,
                    content = it.content,
                    label = "",
                    alarm = it.alarm,
                    id = it.id.toString()
                )
            }
            setState { copy(noteViewState = NoteViewState.Success(r)) }
        }

    }

}