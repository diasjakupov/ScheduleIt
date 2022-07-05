package com.example.scheduleit.data.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleit.data.models.Note
import com.example.scheduleit.data.repository.NoteRepository
import com.example.scheduleit.ui.state.UIState
import com.example.scheduleit.ui.wrappers.CalendarDateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val calendar: Calendar
) : ViewModel(), IGetDateRepresentation {
    val stateUI: MutableState<UIState<Note>> = mutableStateOf(UIState.Loading())

    private val _formattedPickedDate = mutableStateOf(CalendarDateFormat(0, "", 0))
    val formattedPickedDate: State<CalendarDateFormat> get() = _formattedPickedDate
    val date = mutableStateOf(0L)

    suspend fun getTaskById(id: Int) {
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
        stateUI.value = UIState.Loading()

        try {
            val task = repository.getTaskByIdAsync(id)

            calendar.time = Date(task.datetime)
            date.value = task.datetime
            Log.e("TAG", "detail ${date.value}")
            _formattedPickedDate.value = CalendarDateFormat(
                calendar[Calendar.YEAR],
                monthFormat.format(calendar.time),
                calendar[Calendar.DAY_OF_MONTH]
            )

            stateUI.value = UIState.Success(task)
        } catch (e: Exception) {
            e.printStackTrace()
            stateUI.value = UIState.Error(e.message ?: "Unexpected error")
        }

    }


    override fun getDateRepresentation(format: String): String {
        return when (val state = stateUI.value) {
            is UIState.Success<Note> -> {
                SimpleDateFormat(format, Locale.getDefault()).format(Date(state.value.datetime))
            }
            else -> {
                ""
            }
        }
    }
}