package com.example.scheduleit.data.viewModels


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleit.ui.wrappers.CalendarDateFormat
import com.example.scheduleit.data.models.NotificationDelay
import com.example.scheduleit.data.repository.NoteRepository
import com.example.scheduleit.ui.wrappers.OldNewDataHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CreationFormViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val calendar: Calendar
) : ViewModel(), IGetDateRepresentation {

    init {
        calendar.time = Date()
    }

    //time variables
    //for user experience
    private val _formattedPickedDate: MutableState<CalendarDateFormat> = mutableStateOf(
        CalendarDateFormat(year = 0, month = 0, day = 0)
    )
    val formattedPickedDate: State<CalendarDateFormat> get() = _formattedPickedDate

    //date in long format without time
    private val _pickedDate: MutableState<Long> = mutableStateOf(0L)
    private val pickedDate: State<Long> get() = _pickedDate

    private val _hoursAndMinutesInMills: MutableState<Long> = mutableStateOf(0L)

    private val _minAvailableHourValue = mutableStateOf(0)
    val minAvailableHourValue: State<Int> get() = _minAvailableHourValue

    private val _readableTimeRepresentation = mutableStateOf("00:00")

    private val _selectedNotificationDelay: MutableState<Pair<String, Int>> =
        mutableStateOf(NotificationDelay.NOTIFICATION_DELAY.first())
    val selectedNotificationDelay: State<Pair<String, Int>> get() = _selectedNotificationDelay

    val oldNewDataHolder: MutableState<OldNewDataHolder<Pair<Long?, String>>> = mutableStateOf(
        OldNewDataHolder(null, null)
    )

    //text variables
    private val _title: MutableState<String?> = mutableStateOf(null)
    val title: State<String?> get() = _title

    private val _desc: MutableState<String> = mutableStateOf("")
    val desc: State<String> get() = _desc


    //setter
    fun setNewDate(year: Int, month: Int, day: Int) {
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        //set new value
        calendar.set(year, month, day, 0, 0, 0)
        //provide validation in case of selected date is identical to today's date
        _minAvailableHourValue.value = setMinValueForHour(
            today = day, selectedDay = calendar.get(Calendar.DAY_OF_MONTH),
            hour = currentHour
        )

        _pickedDate.value = calendar.timeInMillis
        oldNewDataHolder.value = OldNewDataHolder(
            old = oldNewDataHolder.value.old,
            new = Pair(_pickedDate.value, _readableTimeRepresentation.value)
        )
    }

    fun setNewDate(date: Long) {
        calendar.time = Date(date)

        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
        val currentHour = calendar[Calendar.HOUR_OF_DAY]
        val minutes = calendar[Calendar.MINUTE]


        _minAvailableHourValue.value = currentHour

        _formattedPickedDate.value = CalendarDateFormat(year, month, dayOfMonth)
        _pickedDate.value = calendar.timeInMillis

        _readableTimeRepresentation.value =
            "${String.format("%02d", currentHour)}:${String.format("%02d", minutes)}"

        oldNewDataHolder.value = OldNewDataHolder(
            old = Pair(
                date,
                "${String.format("%02d", currentHour)}:${String.format("%02d", minutes)}"
            ), new = null
        )
    }

    fun setNewTitle(title: String) {
        _title.value = title
    }

    fun setNewDesc(desc: String) {
        _desc.value = desc
    }

    fun setNewTime(hours: Int, minutes: Int) {
        _readableTimeRepresentation.value =
            "${String.format("%02d", hours)}:${String.format("%02d", minutes)}"
        oldNewDataHolder.value =
            OldNewDataHolder(
                old = oldNewDataHolder.value.old,
                new = oldNewDataHolder.value.new?.copy(second = _readableTimeRepresentation.value)
                    ?: Pair(null, _readableTimeRepresentation.value)
            )
        _hoursAndMinutesInMills.value = (hours * 60 * 60 * 1000L) + (minutes * 60 * 1000L)
    }

    fun setNewNotificationDelay(delay: Pair<String, Int>) {
        _selectedNotificationDelay.value = delay
    }


    //formatter
    override fun getDateRepresentation(format: String): String {
        return SimpleDateFormat(format, Locale.getDefault()).format(
            Date(
                oldNewDataHolder.value.new?.first ?: oldNewDataHolder.value.old?.first!!
            )
        )

    }


    fun submitNewTask(): Boolean {
        var valid = validation()
        if (valid) {
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.insertNewNote(
                        title = title.value!!,
                        description = desc.value,
                        datetime = pickedDate.value + _hoursAndMinutesInMills.value,
                        notificationDelay = selectedNotificationDelay.value.second,
                        status = false
                    )
                }
            } catch (e: CancellationException) {
                valid = false
            }
        }
        return valid
    }

    fun submitDateTime() {
        _formattedPickedDate.value = CalendarDateFormat(
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        oldNewDataHolder.value.old = Pair(pickedDate.value, _readableTimeRepresentation.value)
        oldNewDataHolder.value.new = null
    }

    fun cancelDateTime() {
        oldNewDataHolder.value.new = null
    }

    fun reset() {
        val today = calendar.get(Calendar.DAY_OF_YEAR)
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        _formattedPickedDate.value = CalendarDateFormat(
            year = calendar.get(Calendar.YEAR),
            month = calendar[Calendar.MONTH],
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
        _minAvailableHourValue.value = setMinValueForHour(
            today = today, selectedDay = calendar.get(Calendar.DAY_OF_YEAR),
            hour = currentHour
        )

        oldNewDataHolder.value.old = Pair(_pickedDate.value, "00:00")
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


