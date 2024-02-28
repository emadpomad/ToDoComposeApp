package com.example.architecturepractice_todoapp.presentation.task_list_screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.architecturepractice_todoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListDefaultAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onSearchClicked: () -> Unit,
    onSortClicked: () -> Unit,
    onDeleteAllClicked: () -> Unit
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
            IconButton(onClick = { onSortClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_sort_24),
                    contentDescription = "Sort",
                )
            }

            var expandedMenu by remember {
                mutableStateOf(false)
            }
            IconButton(onClick = {
                expandedMenu = !expandedMenu
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_more_vert_24),
                    contentDescription = "More actions",
                )

                DropdownMenu(
                    expanded = expandedMenu,
                    onDismissRequest = {
                        expandedMenu = false
                    }) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Delete All"
                            )
                        },
                        onClick = {
                            expandedMenu = false
                            onDeleteAllClicked()
                        }
                    )
                }

            }
        }

    )

}


@Preview
@Composable
fun PreviewTaskListDefaultAppBar() {
    TaskListDefaultAppBar(
        title = "Tasks",
        onSearchClicked = {},
        onSortClicked = { }
    ) {

    }

}
