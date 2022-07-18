package com.example.scheduleit.ui.navigation.topbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scheduleit.ui.navigation.NavigationRoutes
import com.example.scheduleit.ui.theme.Aqua
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun TabsRow(navHostController: NavHostController) {
    val selectedTabIndex = remember {
        mutableStateOf(1)
    }
    val interactionSource = remember {
        object : MutableInteractionSource {
            override val interactions: Flow<Interaction>
                get() = emptyFlow()

            override suspend fun emit(interaction: Interaction) {}
            override fun tryEmit(interaction: Interaction): Boolean = true
        }
    }
    val tabs = remember {
        mutableStateOf(
            arrayOf(
                TabPath(name = "Monthly", path = NavigationRoutes.MonthScreen.route),
                TabPath(name = "Daily", path = NavigationRoutes.DailyScreen.route)
            )
        )
    }

    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(top = 8.dp, start = 48.dp, end = 48.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(50.dp)),
        indicator = {},
        divider = {}

    ) {
        tabs.value.forEachIndexed { index, path ->
            Tab(
                selected = selectedTabIndex.value == index,
                onClick = {
                    navHostController.navigate(path.path)
                    selectedTabIndex.value = index
                },
                interactionSource = interactionSource,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        animateColorAsState(
                            targetValue = if (selectedTabIndex.value == index) {
                                Aqua
                            } else {
                                MaterialTheme.colors.primary
                            }, animationSpec = tween(500)
                        ).value
                    )
                    .padding(top = 12.dp, bottom = 12.dp)
            ) {
                Text(
                    text = path.name, color = animateColorAsState( targetValue =
                        if (selectedTabIndex.value == index) {
                            Color.White
                        } else {
                            Color.Black
                        }, animationSpec = tween(500)
                    ).value
                )
            }
        }
    }

}


@Composable
@Preview
fun TabsRowPreview() {
    TabsRow(rememberNavController())
}