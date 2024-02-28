package com.example.architecturepractice_todoapp.domain.usecase

import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: TaskModel) {
        taskRepository.deleteTask(task)
    }


}
