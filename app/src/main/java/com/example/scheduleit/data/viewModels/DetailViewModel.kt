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
    private val repository: NoteRepository
) : ViewModel(), IGetDateRepresentation {
    val stateUI: MutableState<UIState<Note>> = mutableStateOf(UIState.Loading())

    suspend fun getTaskById(id: Int) {
        stateUI.value = UIState.Loading()
        try {
            val task = repository.getTaskByIdAsync(id)
            stateUI.value = UIState.Success(task)
        } catch (e: Exception) {
            e.printStackTrace()
            stateUI.value = UIState.Error(e.message ?: "Unexpected error")
        }

    }

    fun deleteByID(id:Int){
        viewModelScope.launch() {
            repository.deleteByID(id)
        }
    }

    override fun getDateRepresentation(format: String, date: Long): String {
        return when (stateUI.value) {
            is UIState.Success<Note> -> {
                SimpleDateFormat(format, Locale.getDefault()).format(Date(date))
            }
            else -> {
                ""
            }
        }
    }
}
