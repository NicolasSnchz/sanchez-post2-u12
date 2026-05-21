package com.nicolassnchz.sanchezpost2u12.data

import com.nicolassnchz.sanchezpost2u12.domain.model.Note
import com.nicolassnchz.sanchezpost2u12.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository : NoteRepository {
    override fun getNotes(): Flow<List<Note>> = flow {
        emit(
            listOf(
                Note(
                    id = "1",
                    title = "Arquitectura limpia",
                    description = "Entidad, caso de uso, repositorio y pantalla separados por capas.",
                    syncStatus = "SYNCED"
                ),
                Note(
                    id = "2",
                    title = "Nota offline",
                    description = "Registro creado localmente y listo para sincronizacion futura.",
                    syncStatus = "PENDING"
                ),
                Note(
                    id = "3",
                    title = "Proyecto integrador",
                    description = "MVP preparado para CI/CD, pruebas y publicacion.",
                    syncStatus = "SYNCED"
                )
            )
        )
    }
}