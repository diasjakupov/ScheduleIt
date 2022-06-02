package com.example.scheduleit.ui.create_dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scheduleit.ui.components.ChooseColor
import com.example.scheduleit.ui.components.CustomTextField
import com.example.scheduleit.ui.theme.Aqua
import com.example.scheduleit.ui.theme.ScheduleItTheme


@ExperimentalComposeUiApi
@Composable
fun CreateDialog(navHostController: NavHostController) {
    val topicText = remember {
        mutableStateOf("")
    }
    val descText = remember {
        mutableStateOf("")
    }

    val isDismissed = remember {
        mutableStateOf(false)
    }

    if (!isDismissed.value) {
        Dialog(onDismissRequest = {
            navHostController.navigate("main_screen")
            isDismissed.value = true
        }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(0.9f)
            ) {
                //TODO(apply logic and implement date picker)
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Column(
                        modifier = Modifier.padding(
                            start = 26.dp,
                            top = 32.dp,
                            end = 26.dp,
                            bottom = 32.dp
                        )
                    ) {
                        //Header
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Create New Tasks", style = TextStyle(
                                    fontWeight = FontWeight(700),
                                    fontSize = 26.sp
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .padding(top = 8.dp, start = 12.dp),
                                thickness = 2.dp
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        //Body
                        Column() {
                            CustomTextField(
                                value = topicText.value,
                                placeholder = "Write Topic",
                                isLabelShown = true,
                                isUnderlined = true,
                                label = "Topic",
                                onValueChange = {
                                    topicText.value = it
                                })

                            Spacer(modifier = Modifier.height(32.dp))

                            CustomTextField(
                                value = descText.value,
                                placeholder = "Write Description",
                                label = "Description",
                                isLabelShown = true,
                                isUnderlined = true,
                                onValueChange = {
                                    descText.value = it
                                })

                            Spacer(modifier = Modifier.height(48.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                CustomTextField(
                                    value = "",
                                    placeholder = "Day",
                                    isUnderlined = true,
                                    readOnly = true,
                                    fullScreen = false,
                                    onValueChange = {
                                    })
                                CustomTextField(
                                    value = "",
                                    placeholder = "Month",
                                    isUnderlined = true,
                                    readOnly = true,
                                    fullScreen = false,
                                    onValueChange = {
                                    })
                                CustomTextField(
                                    value = "",
                                    placeholder = "Year",
                                    isUnderlined = true,
                                    readOnly = true,
                                    fullScreen = false,
                                    onValueChange = {
                                    })
                            }

                            Spacer(modifier = Modifier.height(48.dp))

                            //TODO(change value to list picker)
                            CustomTextField(
                                value = "10 min before",
                                readOnly = true,
                                isUnderlined = true,
                                label = "Notification",
                                isLabelShown = true,
                                onValueChange = {})

                            Spacer(modifier = Modifier.height(48.dp))

                            ChooseColor()
                        }

                    }
                    //Footer
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RectangleShape,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Aqua,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "ADD", style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    }
                }
            }
        }
    }

}


@ExperimentalComposeUiApi
@Composable
@Preview
fun CreateDialogPreview() {
    ScheduleItTheme() {
        CreateDialog(rememberNavController())
    }
}