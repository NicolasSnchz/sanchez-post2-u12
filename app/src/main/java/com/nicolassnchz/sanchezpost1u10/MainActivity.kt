package com.nicolassnchz.sanchezpost1u10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nicolassnchz.sanchezpost1u10.ui.theme.SanchezPost1U10Theme
import com.nicolassnchz.sanchezpost1u11.domain.model.Note
import com.nicolassnchz.sanchezpost1u11.feature.notes.NotesContent
import com.nicolassnchz.sanchezpost1u11.feature.notes.NotesUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SanchezPost1U10Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesDemoApp()
                }
            }
        }
    }
}

@Composable
fun NotesDemoApp() {
    val successNotes = listOf(
        Note(
            id = "1",
            title = "Arquitectura reactiva",
            content = "StateFlow conserva el estado visible de la pantalla."
        ),
        Note(
            id = "2",
            title = "Eventos one-shot",
            content = "SharedFlow evita repetir eventos de navegacion."
        )
    )

    val selectedState = remember {
        mutableStateOf<NotesUiState>(NotesUiState.Success(successNotes))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        Text(
            text = "Modulo Feature Notes",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Demo de StateFlow y SharedFlow",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(onClick = { selectedState.value = NotesUiState.Loading }) {
                Text(text = "Loading")
            }

            Spacer(modifier = Modifier.padding(horizontal = 4.dp))

            Button(onClick = { selectedState.value = NotesUiState.Success(successNotes) }) {
                Text(text = "Success")
            }

            Spacer(modifier = Modifier.padding(horizontal = 4.dp))

            Button(
                onClick = {
                    selectedState.value = NotesUiState.Error("No se pudieron cargar las notas")
                }
            ) {
                Text(text = "Error")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        NotesContent(
            state = selectedState.value,
            onNoteClick = {},
            onDelete = {}
        )
    }
}
