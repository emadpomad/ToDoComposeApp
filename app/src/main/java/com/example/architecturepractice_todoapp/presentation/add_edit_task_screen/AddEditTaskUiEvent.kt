package com.example.architecturepractice_todoapp.presentation.add_edit_task_screen

import com.example.architecturepractice_todoapp.domain.model.CategoryModel
import com.example.architecturepractice_todoapp.domain.model.PriorityModel

sealed class AddEditTaskUiEvent {

    class OnTitleChanged(val newValue: String) : AddEditTaskUiEvent()
    class OnDescriptionChanged(val newValue: String) : AddEditTaskUiEvent()
    class OnPriorityChanged(val newPriority: PriorityModel) : AddEditTaskUiEvent()
    class OnCategoryChanged(val newCategory: CategoryModel) : AddEditTaskUiEvent()
    class OnStatusChanged(val newStatus: Boolean) : AddEditTaskUiEvent()
    class OnTaskSaved():AddEditTaskUiEvent()
    class OnTaskDeleted():AddEditTaskUiEvent()

}
