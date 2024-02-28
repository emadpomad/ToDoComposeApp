package com.example.architecturepractice_todoapp.presentation.task_list_screen.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteBackground(
    modifier: Modifier = Modifier,
    dismissBoxState: SwipeToDismissBoxState,
    positionalThreshold: Float
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(Color.Red)
            .fillMaxHeight()
            .padding(8.dp)
    ) {

        val degree by animateFloatAsState(
            animationSpec = tween(durationMillis = 700),
            targetValue = if (dismissBoxState.progress == 1f || dismissBoxState.progress < positionalThreshold) {
                0f
            } else {
                -45f
            }, label = "Swipe to delete bin rotation animation"
        )


        Icon(
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.CenterEnd)
                .rotate(degree),
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete"
        )


    }

}
