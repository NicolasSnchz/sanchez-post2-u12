package com.nicolassnchz.sanchezpost2u12.feature.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolassnchz.sanchezpost2u12.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NotesUiState>(NotesUiState.Loading)
    val uiState: StateFlow<NotesUiState> = _uiState

    init {
        loadNotes()
    }

    fun loadNotes() {
        viewModelScope.launch {
            _uiState.value = NotesUiState.Loading
            delay(600)

            getNotesUseCase()
                .catch { error ->
                    _uiState.value = NotesUiState.Error(
                        error.message ?: "No se pudieron cargar las notas"
                    )
                }
                .collect { notes ->
                    _uiState.value = NotesUiState.Success(notes)
                }
        }
    }

    fun showLoadingDemo() {
        _uiState.value = NotesUiState.Loading
    }

    fun showErrorDemo() {
        _uiState.value = NotesUiState.Error("Error simulado de red")
    }
}