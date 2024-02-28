package com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.architecturepractice_todoapp.domain.model.PriorityModel
import com.example.architecturepractice_todoapp.util.color
import com.example.architecturepractice_todoapp.util.text

private val priorities = listOf(
    PriorityModel.None,
    PriorityModel.VeryLow,
    PriorityModel.Low,
    PriorityModel.Medium,
    PriorityModel.High,
    PriorityModel.VeryHigh,
).reversed()

@Composable
fun PriorityDropDownMenu(
    modifier: Modifier = Modifier,
    onPrioritySelected: (priority: PriorityModel) -> Unit
) {

    var isMenuExpanded by remember {
        mutableStateOf(false)
    }


    DropdownMenu(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface
        ),
        expanded = isMenuExpanded,
        onDismissRequest = { isMenuExpanded = false }) {


        priorities.forEach { priority ->

            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                text = {

                    Row {
                        Card(
                            modifier = Modifier.size(24.dp),
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = priority.color
                            ),
                            content = {}
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = priority.text,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }

                },
                onClick = {
                    isMenuExpanded = false
                    onPrioritySelected(priority)
                })

        }


    }
}


@Preview
@Composable
fun PreviewPriorityDropDownMenu() {

    PriorityDropDownMenu(
        modifier = Modifier,
        onPrioritySelected = {}
    )

}
