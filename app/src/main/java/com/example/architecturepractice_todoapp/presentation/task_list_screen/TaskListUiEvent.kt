package com.example.architecturepractice_todoapp.presentation.task_list_screen

import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.model.TaskOrder

sealed class TaskListUiEvent {

    class OnTaskDeleted(val task: TaskModel) : TaskListUiEvent()
    class OnTaskStatusChanged(val task: TaskModel, val isDone: Boolean) : TaskListUiEvent()
    class OnSearchQueryChanged(val query: String) : TaskListUiEvent()
    class OnOrderChanged(val order: TaskOrder) : TaskListUiEvent()
    class OnSearchIconClicked : TaskListUiEvent()
    class OnCloseSearch : TaskListUiEvent()
    class OnSearchActionClicked():TaskListUiEvent()
    class OnDeleteAllClicked : TaskListUiEvent()
    class OnDeletedTaskRestored : TaskListUiEvent()

}
