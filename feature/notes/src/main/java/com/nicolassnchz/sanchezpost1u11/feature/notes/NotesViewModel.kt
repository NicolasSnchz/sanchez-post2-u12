package com.nicolassnchz.sanchezpost1u11.feature.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolassnchz.sanchezpost1u11.domain.model.Note
import com.nicolassnchz.sanchezpost1u11.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed class NotesUiState {
    data object Loading : NotesUiState()
    data class Success(val notes: List<Note>) : NotesUiState()
    data class Error(val message: String) : NotesUiState()
}

sealed class NotesEvent {
    data class NavigateToDetail(val noteId: String) : NotesEvent()
    data object NoteDeleted : NotesEvent()
}

class NotesViewModel(
    private val noteRepository: NoteRepository = FakeNoteRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<NotesUiState>(NotesUiState.Loading)
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<NotesEvent>()
    val events: SharedFlow<NotesEvent> = _events.asSharedFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            noteRepository.getNotes()
                .catch { error ->
                    _uiState.value = NotesUiState.Error(error.message ?: "Error cargando notas")
                }
                .collect { notes ->
                    _uiState.value = NotesUiState.Success(notes)
                }
        }
    }

    fun onNoteClicked(noteId: String) {
        viewModelScope.launch {
            _events.emit(NotesEvent.NavigateToDetail(noteId))
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            noteRepository.deleteNote(noteId)
            _events.emit(NotesEvent.NoteDeleted)
        }
    }
}
