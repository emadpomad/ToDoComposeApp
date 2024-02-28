package com.example.architecturepractice_todoapp.presentation.task_list_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturepractice_todoapp.domain.model.OrderType
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.model.TaskOrder
import com.example.architecturepractice_todoapp.presentation.task_list_screen.TaskListUiEvent
import com.example.architecturepractice_todoapp.presentation.task_list_screen.TaskListUiState
import com.example.architecturepractice_todoapp.presentation.task_list_screen.TaskListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskListUseCases: TaskListUseCases
) : ViewModel() {


    private val _state = MutableStateFlow(TaskListUiState())
    val state = _state.asStateFlow()
    private var getTasksJob: Job? = null
    private var searchTasksJob: Job? = null
    private var lastDeletedTask: TaskModel? = null


    init {
        getTasks(taskOrder = TaskOrder.Title(OrderType.Ascending))
    }


    fun onEvent(event: TaskListUiEvent) {
        when (event) {
            is TaskListUiEvent.OnSearchQueryChanged -> {
                _state.value = _state.value.copy(
                    searchQuery = event.query
                )
                searchTasks(
                    query = event.query,
                    taskOrder = _state.value.order
                )
            }

            is TaskListUiEvent.OnTaskDeleted -> {
                deleteTask(event.task)
            }

            is TaskListUiEvent.OnTaskStatusChanged -> {
                changeTaskStatus(event.task, event.isDone)
            }

            is TaskListUiEvent.OnOrderChanged -> {
                _state.value = _state.value.copy(
                    order = event.order
                )
                getTasks(event.order)
            }

            is TaskListUiEvent.OnCloseSearch -> {

                if (state.value.searchQuery.isNotEmpty()) {
                    _state.value = _state.value.copy(
                        searchQuery = ""
                    )
                } else {
                    _state.value = _state.value.copy(
                        isSearching = false
                    )
                }

            }

            is TaskListUiEvent.OnDeleteAllClicked -> {
                viewModelScope.launch {
                    lastDeletedTask?.let { task ->
                        taskListUseCases.addTaskUseCase(task)
                    }
                }
            }

            is TaskListUiEvent.OnSearchActionClicked -> {
                searchTasks(_state.value.searchQuery, _state.value.order)
                _state.value = _state.value.copy(
                    searchQuery = "",
                    isSearching = false
                )
            }

            is TaskListUiEvent.OnSearchIconClicked -> {
                _state.value = _state.value.copy(
                    isSearching = true
                )
            }

            is TaskListUiEvent.OnDeletedTaskRestored -> {
                restoreTask()
            }
        }
    }

    private fun getTasks(taskOrder: TaskOrder) {

        getTasksJob?.cancel()

        getTasksJob = viewModelScope.launch {

            taskListUseCases.getAllTasksUseCase(taskOrder).collectLatest { tasks ->
                _state.value = _state.value.copy(
                    tasks = tasks
                )
            }

        }

    }

    private fun searchTasks(query: String, taskOrder: TaskOrder) {
        searchTasksJob?.cancel()

        searchTasksJob = viewModelScope.launch {

            taskListUseCases.searchTasksUseCase(
                query = query,
                order = taskOrder
            ).collectLatest { searchedTasks ->

                _state.value = _state.value.copy(
                    tasks = searchedTasks
                )

            }

        }

    }

    private fun deleteTask(task: TaskModel) {
        viewModelScope.launch {
            taskListUseCases.deleteTaskUseCase(task)
            lastDeletedTask = task
            _state.value = _state.value.copy(
                showDeletedTaskSnackBar = true,
                lastDeletedTaskName = lastDeletedTask?.title
            )
        }
    }

    private fun restoreTask() {
        viewModelScope.launch {
            taskListUseCases.addTaskUseCase(lastDeletedTask ?: return@launch)
            _state.value = _state.value.copy(
                showDeletedTaskSnackBar = false,
                lastDeletedTaskName = null
            )
        }
    }

    private fun changeTaskStatus(task: TaskModel, isDone: Boolean) {
        viewModelScope.launch {
            taskListUseCases.editTaskUseCase(
                task = task.copy(
                    isDone = isDone
                )
            )
        }
    }

}
