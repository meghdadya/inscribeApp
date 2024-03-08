package com.example.note_presentation.addEdit

import com.example.core_ui.base.BaseContract.*
import com.example.note_presentation.note.NoteUiModel

class AddEditContract {

    sealed class AddEditViewEvent : UiEvent {
        data class GetNote(val noteId: String) : AddEditViewEvent()
        data class AddNote(val note: NoteUiModel) : AddEditViewEvent()
        data class EditNote(val note: NoteUiModel) : AddEditViewEvent()
    }

    data class AddEditState(val addEditStateViewState: AddEditStateViewState) : UiState


    sealed class AddEditStateViewState {
        data object Loading : AddEditStateViewState()
        data class Success(val note: NoteUiModel) : AddEditStateViewState()
    }

    sealed class AddEditViewEffect : UiEffect {

        sealed class Navigation : AddEditViewEffect() {
            data object NavigateBack : Navigation()
        }
    }

}