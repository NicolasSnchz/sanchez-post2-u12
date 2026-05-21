package com.nicolassnchz.sanchezpost2u12.feature.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolassnchz.sanchezpost2u12.domain.model.Note

@Composable
fun NotesRoute(
    viewModel: NotesViewModel,
    selectedNote: Note?,
    onNoteClick: (Note) -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    if (selectedNote != null) {
        NoteDetailScreen(
            note = selectedNote,
            onBack = onBack
        )
    } else {
        NotesScreen(
            state = state,
            onRetry = viewModel::loadNotes,
            onShowLoading = viewModel::showLoadingDemo,
            onShowError = viewModel::showErrorDemo,
            onNoteClick = onNoteClick
        )
    }
}

@Composable
fun NotesScreen(
    state: NotesUiState,
    onRetry: () -> Unit,
    onShowLoading: () -> Unit,
    onShowError: () -> Unit,
    onNoteClick: (Note) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "NotesSync MVP",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Clean Architecture + MVP funcional",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = onShowLoading) {
                Text("Loading")
            }
            Button(onClick = onRetry) {
                Text("Success")
            }
            Button(onClick = onShowError) {
                Text("Error")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            NotesUiState.Loading -> LoadingState()
            is NotesUiState.Success -> SuccessState(
                notes = state.notes,
                onNoteClick = onNoteClick
            )
            is NotesUiState.Error -> ErrorState(
                message = state.message,
                onRetry = onRetry
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(12.dp))
        Text("Cargando notas...")
    }
}

@Composable
private fun SuccessState(
    notes: List<Note>,
    onNoteClick: (Note) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(notes) { note ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNoteClick(note) }
            ) {
                Column(
                    modifier = Modifier.padding(14.dp)
                ) {
                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(text = note.description)
                    Text(text = "Estado: ${note.syncStatus}")
                    Text(text = "Tocar para ver detalle")
                }
            }
        }
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error",
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = message)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onRetry) {
            Text("Reintentar")
        }
    }
}

@Composable
private fun NoteDetailScreen(
    note: Note,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalle de nota",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = note.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Estado de sincronizacion: ${note.syncStatus}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}