package com.example.scheduleit.data.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.scheduleit.data.repository.NoteRepository
import com.example.scheduleit.ui.wrappers.TaskForView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val calendar: Calendar
) : ViewModel(), IGetDateRepresentation {

    init{
        calendar.time = Date()
    }

    private val timeFormatter = SimpleDateFormat("kk:mm", Locale.getDefault())


    private fun getCurrentDate(): Long {
        return calendar.timeInMillis
    }

    override fun getDateRepresentation(format:String): String = SimpleDateFormat(format, Locale.getDefault()).format(
        getCurrentDate()
    )


    fun getNoteList(): Flow<List<TaskForView>> {
        return repository.getAllNotesByDay(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).map { list ->
            list.map { note ->
                TaskForView(
                    id = note.id,
                    name = note.title,
                    time = timeFormatter.format(Date(note.datetime))
                )
            }.sortedBy {
                it.time
            }
        }
    }
}