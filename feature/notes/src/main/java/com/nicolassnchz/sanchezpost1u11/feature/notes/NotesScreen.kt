package com.nicolassnchz.sanchezpost1u11.feature.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicolassnchz.sanchezpost1u11.domain.model.Note

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = viewModel(),
    onNavigateToDetail: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is NotesEvent.NavigateToDetail -> onNavigateToDetail(event.noteId)
                NotesEvent.NoteDeleted -> Unit
            }
        }
    }

    NotesContent(
        state = uiState,
        onNoteClick = viewModel::onNoteClicked,
        onDelete = viewModel::deleteNote
    )
}

@Composable
fun NotesContent(
    state: NotesUiState,
    onNoteClick: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    when (state) {
        NotesUiState.Loading -> LoadingState()
        is NotesUiState.Error -> ErrorState(message = state.message)
        is NotesUiState.Success -> NotesList(
            notes = state.notes,
            onNoteClick = onNoteClick,
            onDelete = onDelete
        )
    }
}

@Composable
fun LoadingState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Cargando notas...")
    }
}

@Composable
fun ErrorState(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error: $message",
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun NotesList(
    notes: List<Note>,
    onNoteClick: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(notes) { note ->
            NoteCard(
                note = note,
                onNoteClick = onNoteClick,
                onDelete = onDelete
            )
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    onNoteClick: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onNoteClick(note.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = note.content)
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.End) {
                Button(onClick = { onDelete(note.id) }) {
                    Text(text = "Eliminar")
                }
            }
        }
    }
}
