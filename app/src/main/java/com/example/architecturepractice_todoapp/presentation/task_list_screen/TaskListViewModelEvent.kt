package com.example.architecturepractice_todoapp.presentation.task_list_screen

import com.example.architecturepractice_todoapp.domain.model.TaskModel

sealed class TaskListViewModelEvent {

    class TaskDeleted(val task: TaskModel) : TaskListViewModelEvent()

}
