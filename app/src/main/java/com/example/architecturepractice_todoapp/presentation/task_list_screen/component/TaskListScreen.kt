package com.example.architecturepractice_todoapp.presentation.task_list_screen.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.*
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.architecturepractice_todoapp.domain.model.CategoryModel
import com.example.architecturepractice_todoapp.domain.model.PriorityModel
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.presentation.task_list_screen.TaskListUiEvent
import com.example.architecturepractice_todoapp.presentation.task_list_screen.TaskListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    state: TaskListUiState,
    navController: NavController,
    onEvent: (event: TaskListUiEvent) -> Unit
) {

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                TaskListDefaultAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Tasks",
                    onSearchClicked = {
                        onEvent(
                            TaskListUiEvent.OnSearchIconClicked()
                        )
                    },
                    onSortClicked = { /*TODO*/ },
                    onDeleteAllClicked = {
                        TaskListUiEvent.OnDeleteAllClicked()
                    }
                )

                AnimatedVisibility(
                    visible = state.isSearching,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 500
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 500
                        )
                    )
                ) {
                    TaskListSearchAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        searchQuery = state.searchQuery,
                        onSearchQueryChanged = {
                            onEvent(
                                TaskListUiEvent.OnSearchQueryChanged(it)
                            )
                        },
                        onSearchActionClicked = {
                            onEvent(
                                TaskListUiEvent.OnSearchActionClicked()
                            )
                        },
                        onCloseClicked = {
                            onEvent(
                                TaskListUiEvent.OnCloseSearch()
                            )
                        }
                    )
                }
            }
        },
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add New Task"
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {


        Column(
            modifier = Modifier
                .padding(it)
                .padding(4.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            )
            {

                items(
                    state.tasks,
                    key = { task ->
                        task.id ?: task.hashCode()
                    }
                ) { task ->
                    var isItemVisible by remember {
                        mutableStateOf(true)
                    }


                    val dismissState = rememberSwipeToDismissBoxState(
                        initialValue = SwipeToDismissBoxValue.Settled,
                        confirmValueChange = { swipeToDismissValue ->
                            if (swipeToDismissValue == SwipeToDismissBoxValue.EndToStart) {
                                isItemVisible = false
                                scope.launch {
                                    delay(700)
                                    onEvent(
                                        TaskListUiEvent.OnTaskDeleted(task)
                                    )
                                }
                                true
                            } else {
                                false
                            }
                        },
                        positionalThreshold = { totalDistance ->
                            (totalDistance / 100) * 40
                        }
                    )


                    AnimatedVisibility(
                        modifier = Modifier.animateItemPlacement(),
                        visible = isItemVisible,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {


                        Column {
                            SwipeToDismissBox(
                                state = dismissState,
                                backgroundContent = {
                                    SwipeToDeleteBackground(
                                        dismissBoxState = dismissState,
                                        positionalThreshold = 0.4f,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                enableDismissFromStartToEnd = false,
                                enableDismissFromEndToStart = true
                            ) {
                                TaskItem(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateItemPlacement(),
                                    task = task,
                                    onTaskClicked = {
                                        //TODO
                                    },
                                    onTaskStatusChanged = { isFinished ->
                                        onEvent(
                                            TaskListUiEvent.OnTaskStatusChanged(task, isFinished)
                                        )
                                    }
                                )
                            }


                            Spacer(modifier = Modifier.height(4.dp))

                        }

                        LaunchedEffect(
                            key1 = state.showDeletedTaskSnackBar,
                            key2 = state.lastDeletedTaskName
                        ) {
                            if (state.showDeletedTaskSnackBar) {
                                val result = snackBarHostState.showSnackbar(
                                    message = "Deleted \"${state.lastDeletedTaskName}\" Successfully.",
                                    actionLabel = "Restore"
                                )
                                when (result) {
                                    Dismissed -> {}
                                    ActionPerformed -> {
                                        onEvent(
                                            TaskListUiEvent.OnDeletedTaskRestored()
                                        )
                                        isItemVisible = true
                                        Log.d("RestoreAction-Before-CurrentValue","${dismissState.currentValue}")
                                        Log.d("RestoreAction-Before-TargetValue","${dismissState.targetValue}")
                                        Log.d("RestoreAction-Before-Progress","${dismissState.progress}")
                                        dismissState.reset()
                                        Log.d("RestoreAction-After-CurrentValue","${dismissState.currentValue}")
                                        Log.d("RestoreAction-After-TargetValue","${dismissState.targetValue}")
                                        Log.d("RestoreAction-After-Progress","${dismissState.progress}")
                                    }
                                }
                            }
                        }
                    }

                }

            }
        }


    }

}


@Preview
@Composable
fun PreviewTaskScreen() {


    TaskListScreen(
        state = TaskListUiState(
            tasks = listOf(
                TaskModel(
                    title = "Task",
                    description = "This is my task.",
                    priority = PriorityModel.VeryLow,
                    category = CategoryModel.Personal,
                    isDone = false,
                    id = 1
                ),
                TaskModel(
                    title = "Task",
                    description = "This is my task.",
                    priority = PriorityModel.VeryHigh,
                    category = CategoryModel.Personal,
                    isDone = false,
                    id = 2
                ),
                TaskModel(
                    title = "Task",
                    description = "This is my task.",
                    priority = PriorityModel.High,
                    category = CategoryModel.Personal,
                    isDone = true,
                    id = 3
                ),

                )
        ),
        navController = rememberNavController(),
        onEvent = {}
    )

}
