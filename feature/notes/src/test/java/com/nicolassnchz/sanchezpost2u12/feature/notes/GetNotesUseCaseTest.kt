package com.nicolassnchz.sanchezpost2u12.feature.notes

import com.nicolassnchz.sanchezpost2u12.domain.model.Note
import com.nicolassnchz.sanchezpost2u12.domain.repository.NoteRepository
import com.nicolassnchz.sanchezpost2u12.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetNotesUseCaseTest {

    @Test
    fun getNotesUseCaseReturnsRepositoryValues() = runTest {
        val expected = listOf(
            Note("1", "Dominio", "Caso de uso probado", "SYNCED")
        )

        val useCase = GetNotesUseCase(
            object : NoteRepository {
                override fun getNotes(): Flow<List<Note>> = flowOf(expected)
            }
        )

        useCase().collect { result ->
            assertEquals(expected, result)
        }
    }
}