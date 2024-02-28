package com.example.architecturepractice_todoapp.presentation.add_edit_task_screen

import com.example.architecturepractice_todoapp.domain.model.CategoryModel
import com.example.architecturepractice_todoapp.domain.model.PriorityModel

data class AddEditTaskUiState(

    val title: String = "",
    val description: String = "",
    val priority: PriorityModel = PriorityModel.None,
    val category: CategoryModel = CategoryModel.Other,
    val isDone: Boolean = false,
    val isAdding : Boolean = false,
    val error: Exception? = null

)
