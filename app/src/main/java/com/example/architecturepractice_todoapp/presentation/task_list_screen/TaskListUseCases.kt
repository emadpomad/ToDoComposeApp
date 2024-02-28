package com.example.architecturepractice_todoapp.presentation.task_list_screen

import com.example.architecturepractice_todoapp.domain.usecase.DeleteTaskUseCase
import com.example.architecturepractice_todoapp.domain.usecase.addedit.AddEditTaskUseCase
import com.example.architecturepractice_todoapp.domain.usecase.tasklist.GetAllTasksUseCase
import com.example.architecturepractice_todoapp.domain.usecase.tasklist.SearchTasksUseCase
import javax.inject.Inject

class TaskListUseCases @Inject constructor(
    val getAllTasksUseCase: GetAllTasksUseCase,
    val searchTasksUseCase: SearchTasksUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val editTaskUseCase: AddEditTaskUseCase,
    val addTaskUseCase : AddEditTaskUseCase
)
