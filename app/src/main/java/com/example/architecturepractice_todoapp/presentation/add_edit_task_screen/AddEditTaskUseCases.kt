package com.example.architecturepractice_todoapp.presentation.add_edit_task_screen

import com.example.architecturepractice_todoapp.domain.usecase.DeleteTaskUseCase
import com.example.architecturepractice_todoapp.domain.usecase.addedit.AddEditTaskUseCase
import com.example.architecturepractice_todoapp.domain.usecase.tasklist.GetTaskUseCase
import javax.inject.Inject

class AddEditTaskUseCases @Inject constructor(

    val addEditTaskUseCase: AddEditTaskUseCase,
    val getTaskUseCase: GetTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase

)
