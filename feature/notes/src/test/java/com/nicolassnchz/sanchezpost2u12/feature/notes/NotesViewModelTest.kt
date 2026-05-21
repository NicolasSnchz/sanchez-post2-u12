package com.nicolassnchz.sanchezpost2u12.feature.notes

import app.cash.turbine.test
import com.nicolassnchz.sanchezpost2u12.domain.model.Note
import com.nicolassnchz.sanchezpost2u12.domain.repository.NoteRepository
import com.nicolassnchz.sanchezpost2u12.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NotesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun uiStateStartsWithLoading() = runTest {
        val repository = SuccessRepository()
        val viewModel = NotesViewModel(GetNotesUseCase(repository))

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is NotesUiState.Loading)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun uiStateEmitsSuccessWhenRepositoryReturnsNotes() = runTest {
        val repository = SuccessRepository()
        val viewModel = NotesViewModel(GetNotesUseCase(repository))

        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is NotesUiState.Success)
            assertEquals(2, (state as NotesUiState.Success).notes.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun uiStateEmitsErrorWhenRepositoryFails() = runTest {
        val repository = ErrorRepository()
        val viewModel = NotesViewModel(GetNotesUseCase(repository))

        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is NotesUiState.Error)
            assertEquals("Network error", (state as NotesUiState.Error).message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun edgeCaseEmptyRepositoryReturnsSuccessWithEmptyList() = runTest {
        val repository = EmptyRepository()
        val viewModel = NotesViewModel(GetNotesUseCase(repository))

        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is NotesUiState.Success)
            assertEquals(0, (state as NotesUiState.Success).notes.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private class SuccessRepository : NoteRepository {
        override fun getNotes(): Flow<List<Note>> = flow {
            emit(
                listOf(
                    Note("1", "Nota 1", "Descripcion 1", "SYNCED"),
                    Note("2", "Nota 2", "Descripcion 2", "PENDING")
                )
            )
        }
    }

    private class ErrorRepository : NoteRepository {
        override fun getNotes(): Flow<List<Note>> = flow {
            throw RuntimeException("Network error")
        }
    }

    private class EmptyRepository : NoteRepository {
        override fun getNotes(): Flow<List<Note>> = flow {
            emit(emptyList())
        }
    }
}