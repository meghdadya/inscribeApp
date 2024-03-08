package com.example.note_presentation.addEdit

import androidx.lifecycle.viewModelScope
import com.example.core_ui.base.BaseViewModel
import com.example.domain.models.NoteDomainModel
import com.example.domain.usecases.note.AddNoteUseCase
import com.example.domain.usecases.note.EditNoteUseCase
import com.example.domain.usecases.note.GetNoteUseCase
import com.example.note_presentation.note.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : BaseViewModel<AddEditContract.AddEditViewEvent, AddEditContract.AddEditState, AddEditContract.AddEditViewEffect>() {
    override fun createInitialState(): AddEditContract.AddEditState {
        return AddEditContract.AddEditState(AddEditContract.AddEditStateViewState.Loading)
    }

    override fun handleEvent(event: AddEditContract.AddEditViewEvent) {
        when (event) {
            is AddEditContract.AddEditViewEvent.GetNote -> {
                viewModelScope.launch {
                    val r = getNoteUseCase.execute(event.noteId.toInt())
                    setState {
                        copy(
                            addEditStateViewState = AddEditContract.AddEditStateViewState.Success(
                                NoteUiModel(
                                    title = r.title,
                                    content = r.content,
                                    label = "",
                                    alarm = r.alarm,
                                    id = r.id.toString()
                                )
                            )
                        )
                    }
                }
            }

            is AddEditContract.AddEditViewEvent.AddNote -> {
                viewModelScope.launch {
                    addNoteUseCase.execute(with(event.note) {
                        NoteDomainModel(
                            id = id?.toInt(),
                            content = content,
                            title = title,
                            alarm = alarm,
                        )
                    })
                }
            }

            is AddEditContract.AddEditViewEvent.EditNote -> {
                viewModelScope.launch {
                    editNoteUseCase.execute(with(event.note) {
                        NoteDomainModel(
                            id = id?.toInt(),
                            content = content,
                            title = title,
                            alarm = alarm,
                        )
                    })
                }
            }
        }
    }
}