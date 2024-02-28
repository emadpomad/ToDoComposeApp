package com.example.architecturepractice_todoapp.presentation.task_list_screen.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.architecturepractice_todoapp.domain.model.CategoryModel
import com.example.architecturepractice_todoapp.domain.model.PriorityModel
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.util.color

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: TaskModel,
    onTaskClicked: () -> Unit,
    onTaskStatusChanged: (isDone: Boolean) -> Unit
) {


    Surface(
        modifier = modifier.clickable {
            onTaskClicked()
        },
        shape = RoundedCornerShape(10),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {

        Row(
            modifier = Modifier
                .drawBehind {
                    drawLine(
                        color = task.priority.color,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = 20f * density
                    )
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

            }

            Spacer(modifier = Modifier.width(4.dp))

            Checkbox(
                checked = task.isDone,
                onCheckedChange = { isChecked ->
                    onTaskStatusChanged(isChecked)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )


        }

    }


}

@Preview(
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun PreviewTaskItemDay() {
    TaskItem(
        task = TaskModel(
            title = "Task",
            description = "This is my task.",
            priority = PriorityModel.High,
            category = CategoryModel.Personal,
            isDone = false,
            id = null
        ),
        onTaskClicked = { /*TODO*/ },
        onTaskStatusChanged = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewTaskItemNight() {
    TaskItem(
        task = TaskModel(
            title = "Task",
            description = "This is my task.",
            priority = PriorityModel.High,
            category = CategoryModel.Personal,
            isDone = false,
            id = null
        ),
        onTaskClicked = { /*TODO*/ },
        onTaskStatusChanged = {},
        modifier = Modifier.fillMaxWidth()
    )
}
