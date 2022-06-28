package com.example.scheduleit.data.viewModels


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleit.data.models.Note
import com.example.scheduleit.ui.wrappers.CalendarDateFormat
import com.example.scheduleit.data.models.NotificationDelay
import com.example.scheduleit.data.repository.NoteRepository
import com.example.scheduleit.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CreationFormViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val calendar: Calendar
) : ViewModel() {

    init {
        Log.e("TAG", "initialize CreationFormViewModel")
        calendar.time = Date()
    }

    private val _stateUI: MutableState<UIState<Note>> =
        mutableStateOf(UIState.Loading())
    val stateUI: State<UIState<Note>> get() = _stateUI

    private val _formattedPickedDate: MutableState<CalendarDateFormat> = mutableStateOf(
        CalendarDateFormat(year = 0, monthName = "Nov", day = 0)
    )
    val formattedPickedDate: State<CalendarDateFormat> get() = _formattedPickedDate

    private val _pickedDate: MutableState<Long> = mutableStateOf(0L)
    val pickedDate: State<Long> get() = _pickedDate

    private val _title: MutableState<String?> = mutableStateOf(null)
    val title: State<String?> get() = _title

    private val _desc: MutableState<String> = mutableStateOf("")
    val desc: State<String> get() = _desc

    private val _time: MutableState<Long> = mutableStateOf(0L)

    private val _minHour = mutableStateOf(0)
    val minHour: State<Int> get() = _minHour

    private val _formattedTime = mutableStateOf("00:00")
    val formattedTime: State<String> get() = _formattedTime

    private val _selectedNotificationDelay: MutableState<Pair<String, Int>> =
        mutableStateOf(NotificationDelay.NOTIFICATION_DELAY.first())
    val selectedNotificationDelay: State<Pair<String, Int>> get() = _selectedNotificationDelay

    //setter
    fun setNewDate(year: Int, month: Int, day: Int) {
        val format = SimpleDateFormat("MMM", Locale.getDefault())
        val today = calendar.get(Calendar.DAY_OF_YEAR)
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        Log.e("TAG", "$year, $month, $day")
        //set new value
        calendar.set(year, month, day, 0, 0, 0)

        //provide validation in case of selected date is identical to today's date
        _minHour.value = setMinValueForHour(
            today = today, selectedDay = calendar.get(Calendar.DAY_OF_YEAR),
            hour = currentHour
        )
        //

        _formattedPickedDate.value = CalendarDateFormat(year, format.format(calendar.time), day)
        _pickedDate.value = calendar.timeInMillis
    }

    fun setNewTitle(title: String) {
        _title.value = title
    }

    fun setNewDesc(desc: String) {
        _desc.value = desc
    }

    fun setNewTime(hours: Int, minutes: Int) {
        _formattedTime.value = "${String.format("%02d", hours)}:${String.format("%02d", minutes)}"
        _time.value = (hours * 60 * 60 * 1000L) + (minutes * 60 * 1000L)
        Log.e("TAG", "${_time.value}")
    }

    fun setNewNotificationDelay(delay: Pair<String, Int>) {
        _selectedNotificationDelay.value = delay
    }


    //formatter
    fun getDateRepresentation(): String {
        val format = SimpleDateFormat("MMMM d, y", Locale.getDefault())
        return format.format(Date(pickedDate.value))
    }


    fun submit(): Boolean {
        val valid = validation()
        if (valid) {
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.insertNewNote(
                        title = title.value!!,
                        description = desc.value,
                        datetime = pickedDate.value + _time.value,
                        notificationDelay = selectedNotificationDelay.value.second,
                        status = false
                    )
                }
            } catch (e: CancellationException) {
                _stateUI.value =
                    UIState.Error(e.message ?: "Oops, something has gone wrong")
            }
        } else {
            _stateUI.value = UIState.Error("Some fields are not used correctly")
        }
        return valid
    }

    fun reset() {
        _stateUI.value = UIState.Loading()
        try {
            val format = SimpleDateFormat("MMM", Locale.getDefault())
            val today = calendar.get(Calendar.DAY_OF_YEAR)
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            _formattedPickedDate.value = CalendarDateFormat(
                year = calendar.get(Calendar.YEAR),
                monthName = format.format(calendar.timeInMillis),
                day = calendar.get(Calendar.DAY_OF_MONTH)
            )
            calendar.set(
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH],
                0,
                0,
                0
            )
            _pickedDate.value = calendar.timeInMillis
            _title.value = null
            _desc.value = ""
            _minHour.value = setMinValueForHour(
                today = today, selectedDay = calendar.get(Calendar.DAY_OF_YEAR),
                hour = currentHour
            )
        } catch (e: Exception) {
            _stateUI.value =
                UIState.Error(e.message ?: "Oops, something has gone wrong")
        }

    }

    private fun setMinValueForHour(hour: Int, today: Int, selectedDay: Int): Int {
        return if (today == selectedDay) {
            hour
        } else {
            0
        }
    }

    private fun validation(): Boolean {
        val titleValidation = title.value != ""
        val timeValidation = true // TODO(add proper time validation)
        return titleValidation && titleValidation
    }
}