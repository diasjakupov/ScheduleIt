package com.example.scheduleit.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.data.models.Note
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.data.viewModels.MainScreenViewModel
import com.example.scheduleit.ui.wrappers.TaskForView
import com.example.scheduleit.ui.components.TaskItem
import com.example.scheduleit.ui.theme.ExtremeLightGrey

@Composable
fun ItemList(VM: MainScreenViewModel, paddingValues: PaddingValues) {

    val noteList: State<List<TaskForView>> = VM.noteList.collectAsState(initial = emptyList())


    LazyColumn(
        modifier = Modifier
            .background(ExtremeLightGrey)
            .padding(paddingValues = paddingValues)
            .fillMaxHeight().fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(noteList.value) {
            TaskItem(it)
        }
    }
}


@Composable
@Preview
fun ItemListPreview(){
    ItemList(viewModel(),PaddingValues(start = 48.dp, end = 48.dp, top = 18.dp))
}