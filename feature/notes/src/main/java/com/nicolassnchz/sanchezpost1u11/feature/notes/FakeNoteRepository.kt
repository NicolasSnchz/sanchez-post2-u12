package com.nicolassnchz.sanchezpost1u11.feature.notes

import com.nicolassnchz.sanchezpost1u11.domain.model.Note
import com.nicolassnchz.sanchezpost1u11.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeNoteRepository : NoteRepository {
    private val notes = MutableStateFlow(
        listOf(
            Note(
                id = "1",
                title = "Arquitectura reactiva",
                content = "StateFlow mantiene el estado visible de la pantalla."
            ),
            Note(
                id = "2",
                title = "Eventos one-shot",
                content = "SharedFlow evita repetir eventos de navegacion."
            )
        )
    )

    override fun getNotes(): Flow<List<Note>> = notes

    override suspend fun addNote(note: Note) {
        notes.value = notes.value + note
    }

    override suspend fun deleteNote(id: String) {
        notes.value = notes.value.filterNot { it.id == id }
    }
}
