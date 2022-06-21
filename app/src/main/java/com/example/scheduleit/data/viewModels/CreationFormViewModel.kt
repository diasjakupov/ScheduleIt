package com.example.scheduleit.data.viewModels


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.scheduleit.data.models.CalendarDateFormat
import com.example.scheduleit.data.models.NotificationDelay
import com.example.scheduleit.ui.state.CreateDialogUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CreationFormViewModel @Inject constructor() : ViewModel() {

    private val _stateUI: MutableState<CreateDialogUIState> =
        mutableStateOf(CreateDialogUIState.Loading)
    val stateUI: State<CreateDialogUIState> get() = _stateUI

    private val _formattedPickedDate: MutableState<CalendarDateFormat> = mutableStateOf(
        CalendarDateFormat(year = 0, monthName = "Nov", day = 0)
    )
    val formattedPickedDate: State<CalendarDateFormat> get() = _formattedPickedDate

    private val _pickedDate: MutableState<Long> = mutableStateOf(0L)
    val pickedDate: State<Long> get() = _pickedDate

    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> get() = _title

    private val _desc: MutableState<String> = mutableStateOf("")
    val desc: State<String> get() = _desc

    private val _time: MutableState<Long> = mutableStateOf(0L)

    private val _formattedTime = mutableStateOf("00:00")
    val formattedTime: State<String> get() = _formattedTime



    private val _selectedNotificationDelay: MutableState<Pair<String, Int>> =
        mutableStateOf(NotificationDelay.BEFORE_10_STR to NotificationDelay.BEFORE_10)
    val selectedNotificationDelay: State<Pair<String, Int>> get() = _selectedNotificationDelay

    //setter
    fun setNewDate(year: Int, month: Int, day: Int) {
        val cal = Calendar.getInstance()
        val format = SimpleDateFormat("MMM", Locale.getDefault())
        cal.set(year, month, day)
        _formattedPickedDate.value = CalendarDateFormat(year, format.format(cal.time), day)
        _pickedDate.value = cal.timeInMillis
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
    }

    fun setNewNotificationDelay(delay: Pair<String, Int>){
        _selectedNotificationDelay.value = delay
    }


    //formatter
    fun getDateRepresentation(): String {
        val format = SimpleDateFormat("MMMM d, y", Locale.getDefault())
        return format.format(Date(pickedDate.value))
    }


    fun submit(): Boolean {
        val validation = title.value != ""
        //TODO(add to database)
        return validation
    }

    fun reset() {
        _stateUI.value = CreateDialogUIState.Loading
        try {
            val cal = Calendar.getInstance()
            val format = SimpleDateFormat("MMM", Locale.getDefault())
            _formattedPickedDate.value = CalendarDateFormat(
                year = cal.get(Calendar.YEAR),
                monthName = format.format(cal.timeInMillis),
                day = cal.get(Calendar.DAY_OF_MONTH)
            )
            _pickedDate.value = cal.timeInMillis
            _title.value = ""
            _desc.value = ""
            _stateUI.value = CreateDialogUIState.Success
        } catch (e: Exception) {
            _stateUI.value = CreateDialogUIState.Error
        }

    }
}