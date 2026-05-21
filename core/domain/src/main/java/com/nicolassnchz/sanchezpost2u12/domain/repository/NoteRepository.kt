package com.nicolassnchz.sanchezpost2u12.domain.repository

import com.nicolassnchz.sanchezpost2u12.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
}