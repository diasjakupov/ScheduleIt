package com.example.scheduleit.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scheduleit.data.models.Note
import com.example.scheduleit.data.viewModels.DetailViewModel
import com.example.scheduleit.ui.state.UIState

@Composable
fun DetailDialog(id: Int, VM: DetailViewModel = hiltViewModel(), onDismiss: () -> Unit) {
    LaunchedEffect(key1 = true, block = {
        VM.getTaskById(id)
    })

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue),
            shape = RoundedCornerShape(6.dp)
        ) {
            //Header
            when(val state: UIState<Note> = VM.stateUI.value){
                is UIState.Success ->{
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(state.value.title)
                    }
                }
                is UIState.Error->{
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(state.errorMessage)
                    }
                }
                else -> {}
            }


        }
    }
}