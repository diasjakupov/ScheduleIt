package com.example.scheduleit.data.viewModels

import androidx.lifecycle.ViewModel
import com.example.scheduleit.data.repository.NoteRepository
import com.example.scheduleit.ui.wrappers.TaskForView
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    repository: NoteRepository
): ViewModel() {
    private val timeFormatter = SimpleDateFormat("k:m", Locale.getDefault())

    val noteList = repository.getAllNotes().map { list->
        list.map { note->
            TaskForView(
                id = note.id,
                name = note.title,
                time = timeFormatter.format(Date(note.datetime))
            )
        }
    }
}