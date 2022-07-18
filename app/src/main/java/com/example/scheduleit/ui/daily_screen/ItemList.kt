package com.example.scheduleit.ui.daily_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scheduleit.data.viewModels.MainScreenViewModel
import com.example.scheduleit.ui.wrappers.TaskForView
import com.example.scheduleit.ui.components.TaskItem

@Composable
fun ItemList(
    VM: MainScreenViewModel,
    top: Dp,
    onClick: (id:Int) -> Unit
) {
    val noteList: State<List<TaskForView>> = VM.getNoteList().collectAsState(initial = emptyList())


    LazyColumn(
        modifier = Modifier
            .padding(top = top)
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(noteList.value) {
            TaskItem(it) {
                onClick(it.id)
            }
        }
    }

}


@Composable
@Preview
fun ItemListPreview() {
    ItemList(
        viewModel(),top = 18.dp,
        onClick = {}
    )
}