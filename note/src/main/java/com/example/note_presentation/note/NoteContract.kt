package com.example.note_presentation.note

import com.example.core_ui.base.BaseContract.*

class NoteContract {

    sealed class NoteViewEvent : UiEvent {
        data object GetNotes : NoteViewEvent()
    }

    data class NoteState(val noteViewState: NoteViewState) : UiState


    sealed class NoteViewState {
        data object Loading : NoteViewState()
        data class Success(val notes: List<NoteUiModel>) : NoteViewState()
    }

    sealed class NoteViewEffect : UiEffect {

        sealed class Navigation : NoteViewEffect() {
            data class ToRepos(val noteId: Int) : Navigation()
        }
    }

}