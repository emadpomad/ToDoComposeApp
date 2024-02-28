package com.example.architecturepractice_todoapp.domain.model

data class TaskModel(
    val title: String,
    val description: String,
    val priority: PriorityModel,
    val category: CategoryModel,
    val isDone: Boolean,
    val id: Int?
)

class InvalidTaskException(msg: String) : Exception(msg)
