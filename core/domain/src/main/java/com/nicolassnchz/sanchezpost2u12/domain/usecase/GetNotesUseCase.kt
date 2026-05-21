package com.nicolassnchz.sanchezpost2u12.domain.usecase

import com.nicolassnchz.sanchezpost2u12.domain.model.Note
import com.nicolassnchz.sanchezpost2u12.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> = repository.getNotes()
}