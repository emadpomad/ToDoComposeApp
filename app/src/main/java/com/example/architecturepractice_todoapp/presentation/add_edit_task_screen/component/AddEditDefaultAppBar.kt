package com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDefaultAppBar(
    modifier: Modifier = Modifier,
    onSaveActionClicked: () -> Unit,
    onNavigateUpClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    isAdding: Boolean
) {

    TopAppBar(
        title = {
            Text(
                text = if (isAdding) "Add Task" else "Edit Task"
            )
        },
        actions = {

            IconButton(onClick = { onSaveActionClicked() }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }

            if (!isAdding) {

                IconButton(onClick = {
                    onDeleteClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationIcon = {
            IconButton(onClick = {
                onNavigateUpClicked()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate Up"
                )
            }
        }
    )

}

@Preview
@Composable
fun PreviewAddEditDefaultAppBar() {

    AddEditDefaultAppBar(
        onSaveActionClicked = { /*TODO*/ },
        onNavigateUpClicked = { /*TODO*/ },
        onDeleteClicked = { /*TODO*/ },
        isAdding = false
    )

}
