package com.example.scheduleit.ui.detail

import androidx.compose.animation.animateContentSize

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.scheduleit.data.models.Note
import com.example.scheduleit.data.viewModels.CreationFormViewModel
import com.example.scheduleit.data.viewModels.DetailViewModel
import com.example.scheduleit.ui.components.CreateBtn
import com.example.scheduleit.ui.components.CustomTextField
import com.example.scheduleit.ui.components.Loader
import com.example.scheduleit.ui.components.TaskHeader
import com.example.scheduleit.ui.state.UIState
import com.example.scheduleit.ui.theme.Aqua

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailDialog(
    id: Int,
    detailVM: DetailViewModel,
    creationVM: CreationFormViewModel,
    onDismiss: () -> Unit
) {
    val changeMod = remember {
        mutableStateOf(false)
    }
    val isDateEditBlockShown = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true, block = {
        detailVM.getTaskById(id)
        creationVM.setNewDate(detailVM.date.value)
    })


    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .animateContentSize(
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                ),
            shape = RoundedCornerShape(6.dp)
        ) {
            //Header
            when (val state: UIState<Note> = detailVM.stateUI.value) {
                is UIState.Success -> {
                    //main body
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Column(
                            modifier = Modifier.padding(
                                top = 12.dp, bottom = 12.dp, start = 22.dp, end = 36.dp
                            )
                        ) {
                            //Header
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(16.dp)
                                        .background(color = Aqua)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                if(changeMod.value){
                                    CustomTextField(
                                        value = creationVM.title.value ?: "",
                                        placeholder = "Title",
                                        onValueChange = { creationVM.setNewTitle(it) })
                                }else{
                                    TaskHeader(
                                        title = state.value.title, textStyle = TextStyle(
                                            fontWeight = FontWeight(500),
                                            fontSize = 20.sp
                                        ), modifier = Modifier.clickable {
                                            changeMod.value = true
                                        }
                                    )
                                }

                            }
                            CreateBtn(
                                format = "d MMM kk:mm", textStyle = TextStyle(
                                    fontWeight = FontWeight.Normal, fontSize = 16.sp
                                ), horizontal = Arrangement.Start, reversed = true, VM = detailVM
                            ) {
                                changeMod.value = true
                                isDateEditBlockShown.value = true
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            if(changeMod.value){
                                CustomTextField(
                                    value = creationVM.desc.value,
                                    placeholder = "Description",
                                    onValueChange = {creationVM.setNewDesc(it)})
                            }else{
                                Text(state.value.description, modifier = Modifier.clickable {
                                    changeMod.value = true
                                })
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Row() {
                                Icon(
                                    imageVector = Icons.Outlined.Done,
                                    contentDescription = "Done Icon",
                                    tint = if (state.value.status) {
                                        Color.Green
                                    } else {
                                        Color.Gray
                                    },
                                    modifier = Modifier.padding(end = 12.dp)
                                )
                                Text(
                                    "Completed", color = if (state.value.status) {
                                        Color.Green
                                    } else {
                                        Color.LightGray
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Row(modifier = Modifier.fillMaxWidth()){
                            if(changeMod.value){
                                Button(
                                    onClick = { changeMod.value = false/*TODO*/ },
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    shape = RectangleShape,
                                    contentPadding = PaddingValues(14.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Aqua,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("edit".uppercase())
                                }
                                Button(
                                    onClick = { changeMod.value = false
                                                onDismiss()/*TODO*/ },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RectangleShape,
                                    contentPadding = PaddingValues(14.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Red,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("cancel".uppercase())
                                }
                            }else{
                                Button(
                                    onClick = { onDismiss() /*TODO*/ },
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    shape = RectangleShape,
                                    contentPadding = PaddingValues(14.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Green,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("done".uppercase())
                                }
                                Button(
                                    onClick = { onDismiss() /*TODO*/ },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RectangleShape,
                                    contentPadding = PaddingValues(14.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.error,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text("delete".uppercase())
                                }
                            }
                        }

                    }

                    //for edit mode
                    if(changeMod.value && isDateEditBlockShown.value){
                        DateTimeEditBlock() {
                            changeMod.value = false
                        }
                    }
                }

                is UIState.Error -> {
                    Box(modifier = Modifier.height(150.dp), contentAlignment = Alignment.Center) {
                        Text(state.errorMessage)
                    }
                }

                is UIState.Loading -> {
                    Box(modifier = Modifier.height(150.dp), contentAlignment = Alignment.Center) {
                        Loader(
                            compSize = 55.dp,
                            startAngle = 0f,
                            sweepAngle = 270f,
                            strokeWidth = 4.dp
                        )
                    }

                }
            }


        }
    }
}