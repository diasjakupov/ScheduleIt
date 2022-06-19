package com.example.scheduleit.data.viewModels


import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.scheduleit.data.models.CalendarDateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import javax.inject.Inject


@HiltViewModel
class CreationFormViewModel @Inject constructor() : ViewModel() {

    private val _formattedPickedDate: MutableState<CalendarDateFormat> = mutableStateOf(
        CalendarDateFormat(0, 0, 0)
    )
    val formattedPickedDate get() = _formattedPickedDate

    private val _pickedDate: MutableState<Long> = mutableStateOf(0L)
    val pickedDate get() = _pickedDate

    private val _title: MutableState<String> = mutableStateOf("")
    val title get() = _title

    private val _desc: MutableState<String> = mutableStateOf("")
    val desc get() = _desc


    fun reset() {
        val cal = Calendar.getInstance()

        _formattedPickedDate.value = CalendarDateFormat(
            year = cal.get(Calendar.YEAR),
            month = cal.get(Calendar.MONTH),
            day = cal.get(Calendar.DAY_OF_MONTH)
        )
        _pickedDate.value = cal.timeInMillis
        _title.value = ""
        _desc.value = ""
    }

    fun setNewDate(year: Int, month: Int, day: Int) {
        val cal = Calendar.getInstance()
        cal.set(year, month, day)
        _formattedPickedDate.value = CalendarDateFormat(year, month, day)
        _pickedDate.value = cal.timeInMillis
    }

    fun setNewTitle(title:String){
        _title.value = title
    }

    fun setNewDesc(desc: String){
        _desc.value = desc
    }


    fun getDateRepresentation(): String {
        val format = SimpleDateFormat("MMMM d, y", Locale.getDefault())
        return format.format(Date(pickedDate.value))
    }

    fun submit(){}
}