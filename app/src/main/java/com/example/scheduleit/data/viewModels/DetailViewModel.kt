package com.example.scheduleit.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleit.data.models.Note
import com.example.scheduleit.data.repository.NoteRepository
import com.example.scheduleit.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    val stateUI: MutableState<UIState<Note>> = mutableStateOf(UIState.Loading())

    fun getTaskById(id: Int) {
        stateUI.value = UIState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val task = repository.getTaskByIdAsync(id)
                stateUI.value = UIState.Success(task)
            } catch (e: Exception) {
                e.printStackTrace()
                stateUI.value = UIState.Error(e.message ?: "Unexpected error")
            }

        }
    }
}
