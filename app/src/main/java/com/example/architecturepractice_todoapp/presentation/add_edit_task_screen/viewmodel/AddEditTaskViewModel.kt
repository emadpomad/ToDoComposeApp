package com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.AddEditTaskUiEvent
import com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.AddEditTaskUiState
import com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.AddEditTaskUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AddEditTaskViewModel.AddEditTaskViewModelFactory::class)
class AddEditTaskViewModel @AssistedInject constructor(
    private val addEditTaskUseCases: AddEditTaskUseCases,
    @Assisted private val taskID: Int = -1
) : ViewModel() {


    @AssistedFactory
    interface AddEditTaskViewModelFactory {
        fun create(taskID: Int): AddEditTaskViewModel
    }

    private val _state = MutableStateFlow(AddEditTaskUiState())
    val state = _state.asStateFlow()
    private var getTaskJob: Job? = null


    init {
        if (taskID != -1) {
            getTask(taskID)
        }
    }

    fun onEvent(event: AddEditTaskUiEvent) {
        when (event) {
            is AddEditTaskUiEvent.OnCategoryChanged -> {
                _state.value = _state.value.copy(
                    category = event.newCategory
                )
            }

            is AddEditTaskUiEvent.OnDescriptionChanged -> {
                _state.value = _state.value.copy(
                    description = event.newValue
                )
            }

            is AddEditTaskUiEvent.OnPriorityChanged -> {
                _state.value = _state.value.copy(
                    priority = event.newPriority
                )
            }

            is AddEditTaskUiEvent.OnStatusChanged -> {
                _state.value = _state.value.copy(
                    isDone = event.newStatus
                )
            }

            is AddEditTaskUiEvent.OnTitleChanged -> {
                _state.value = _state.value.copy(
                    title = event.newValue
                )
            }

            is AddEditTaskUiEvent.OnTaskDeleted -> {
                deleteTask()
            }

            is AddEditTaskUiEvent.OnTaskSaved -> {
                try {
                    saveTask()
                } catch (e: Exception) {
                    _state.value = state.value.copy(
                        error = e
                    )
                }

            }
        }
    }

    private fun getTask(taskID: Int) {

        getTaskJob?.cancel()

        getTaskJob = viewModelScope.launch {

            addEditTaskUseCases.getTaskUseCase(taskID).collectLatest { task ->

                _state.value = _state.value.copy(
                    title = task.title,
                    description = task.description,
                    priority = task.priority,
                    category = task.category,
                    isDone = task.isDone,
                )


            }

        }

    }

    private fun saveTask() {
        viewModelScope.launch {
            addEditTaskUseCases.addEditTaskUseCase(
                task = TaskModel(
                    title = state.value.title,
                    description = state.value.description,
                    priority = state.value.priority,
                    category = state.value.category,
                    isDone = state.value.isDone,
                    id = null//if (taskID == -1) null else taskID,
                )
            )

        }
    }

    private fun deleteTask() {
        viewModelScope.launch {
            addEditTaskUseCases.deleteTaskUseCase(
                task = TaskModel(
                    title = state.value.title,
                    description = state.value.description,
                    priority = state.value.priority,
                    category = state.value.category,
                    isDone = state.value.isDone,
                    id = taskID
                )
            )
        }
    }

}
