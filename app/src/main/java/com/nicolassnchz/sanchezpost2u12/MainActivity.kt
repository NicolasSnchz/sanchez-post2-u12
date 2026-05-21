package com.nicolassnchz.sanchezpost2u12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.nicolassnchz.sanchezpost2u12.data.FakeNoteRepository
import com.nicolassnchz.sanchezpost2u12.domain.model.Note
import com.nicolassnchz.sanchezpost2u12.domain.usecase.GetNotesUseCase
import com.nicolassnchz.sanchezpost2u12.feature.notes.NotesRoute
import com.nicolassnchz.sanchezpost2u12.feature.notes.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = FakeNoteRepository()
        val getNotesUseCase = GetNotesUseCase(repository)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val selectedNote = remember { mutableStateOf<Note?>(null) }
                    val viewModel = remember { NotesViewModel(getNotesUseCase) }

                    NotesRoute(
                        viewModel = viewModel,
                        selectedNote = selectedNote.value,
                        onNoteClick = { note -> selectedNote.value = note },
                        onBack = { selectedNote.value = null }
                    )
                }
            }
        }
    }
}
