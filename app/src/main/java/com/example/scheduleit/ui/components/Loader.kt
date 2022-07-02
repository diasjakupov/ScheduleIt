package com.example.scheduleit.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.example.scheduleit.ui.theme.Aqua

@Composable
fun Loader(
    compSize: Dp,
    startAngle: Float,
    sweepAngle: Float,
    strokeWidth: Dp
) {
    val infiniteAnim = rememberInfiniteTransition()
    val start = infiniteAnim.animateFloat(
        initialValue = startAngle,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
        )
    )
    Canvas(modifier = Modifier.size(compSize)) {
        drawArc(
            color = Aqua,
            startAngle = start.value,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(
                width = strokeWidth.toPx(), cap = StrokeCap.Round
            ),
            size = Size(size.width, size.width)
        )
    }
}