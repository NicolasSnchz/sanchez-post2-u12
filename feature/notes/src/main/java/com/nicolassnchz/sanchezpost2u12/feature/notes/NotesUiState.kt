package com.nicolassnchz.sanchezpost2u12.feature.notes

import com.nicolassnchz.sanchezpost2u12.domain.model.Note

sealed interface NotesUiState {
    data object Loading : NotesUiState
    data class Success(val notes: List<Note>) : NotesUiState
    data class Error(val message: String) : NotesUiState
}