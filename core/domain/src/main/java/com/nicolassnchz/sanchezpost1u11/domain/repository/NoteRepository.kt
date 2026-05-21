package com.nicolassnchz.sanchezpost1u11.domain.repository

import com.nicolassnchz.sanchezpost1u11.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(id: String)
}
