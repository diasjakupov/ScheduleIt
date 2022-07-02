package com.example.scheduleit.ui.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.scheduleit.data.models.Note
import com.example.scheduleit.data.viewModels.DetailViewModel
import com.example.scheduleit.ui.components.CreateBtn
import com.example.scheduleit.ui.components.Loader
import com.example.scheduleit.ui.components.TaskHeader
import com.example.scheduleit.ui.state.UIState
import com.example.scheduleit.ui.theme.Aqua
import org.intellij.lang.annotations.JdkConstants

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailDialog(
    id: Int,
    navController: NavController,
    VM: DetailViewModel,
    onDismiss: () -> Unit
) {

    LaunchedEffect(key1 = true, block = {
        VM.getTaskById(id)
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
                    animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing)
                ),
            shape = RoundedCornerShape(6.dp)
        ) {
            //Header
            when (val state: UIState<Note> = VM.stateUI.value) {
                is UIState.Success -> {
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
                                TaskHeader(
                                    title = state.value.title, textStyle = TextStyle(
                                        fontWeight = FontWeight(500),
                                        fontSize = 20.sp
                                    )
                                )
                            }
                            CreateBtn(
                                format = "d MMM kk:mm", textStyle = TextStyle(
                                    fontWeight = FontWeight.Normal, fontSize = 16.sp
                                ), horizontal = Arrangement.Start, reversed = true, VM = VM
                            ) {
                                navController.navigate("create_dialog")
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(state.value.description)
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
                                        MaterialTheme.colors.onPrimary
                                    } else {
                                        Color.LightGray
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
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
                is UIState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(state.errorMessage)
                    }
                }
                is UIState.Loading -> {
                    Box(modifier = Modifier.height(200.dp), contentAlignment = Alignment.Center) {
                        Loader(
                            compSize = 60.dp,
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