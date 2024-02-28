package com.example.architecturepractice_todoapp.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.architecturepractice_todoapp.presentation.task_list_screen.component.SwipeToDeleteBackground

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TestSwipe() {

    var showItem by remember {
        mutableStateOf(true)
    }

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {

            if (it == SwipeToDismissBoxValue.EndToStart) {
                Log.d("dismissState",it.toString())
                showItem = false
                true
            } else {
                false
            }
        },
        positionalThreshold = { totalDistance ->
            (totalDistance / 100) * 20
        }
    )




    AnimatedVisibility(
        visible = showItem,
        enter = expandVertically(animationSpec = tween(durationMillis = 500)),
        exit = shrinkVertically(animationSpec = tween(durationMillis = 500))
    ) {
        SwipeToDismissBox(
            modifier = Modifier
                .fillMaxWidth(),
            state = dismissState,
            enableDismissFromEndToStart = true,
            enableDismissFromStartToEnd = false,
            backgroundContent = {
                SwipeToDeleteBackground(
                    dismissBoxState = dismissState,
                    modifier = Modifier.fillMaxWidth(),
                    positionalThreshold = 0.2F
                )
            }
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
            ) {
                Text(
                    text = "Helloooo",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 36.sp
                )
            }

        }
    }


}
