package com.example.architecturepractice_todoapp.domain.usecase.addedit

import com.example.architecturepractice_todoapp.domain.model.InvalidTaskException
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.repository.TaskRepository
import javax.inject.Inject

class AddEditTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(
        task: TaskModel
    ) {

        if (task.title.isBlank()){
            throw InvalidTaskException("Title of the task can not be empty.")
        }



        if (task.id == null) {
            taskRepository.addTask(task)
        } else {
            taskRepository.editTask(task)
        }
    }

}
