package com.example.architecturepractice_todoapp.presentation.task_list_screen

import com.example.architecturepractice_todoapp.domain.model.OrderType
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.model.TaskOrder

data class TaskListUiState(
    val tasks: List<TaskModel> = emptyList(),
    val order: TaskOrder = TaskOrder.Title(OrderType.Ascending),
    val isSearching: Boolean = false,
    val searchQuery: String = "",
    val showDeletedTaskSnackBar: Boolean = false,
    val lastDeletedTaskName : String? = null
)
