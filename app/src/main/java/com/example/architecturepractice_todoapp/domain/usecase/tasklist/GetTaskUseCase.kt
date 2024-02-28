package com.example.architecturepractice_todoapp.domain.usecase.tasklist

import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(taskID: Int): Flow<TaskModel> {
        return taskRepository.getTaskByID(taskID)
    }

}
