package com.example.architecturepractice_todoapp.domain.repository

import com.example.architecturepractice_todoapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<TaskModel>>

//    fun searchAllTasks(query: String): Flow<List<TaskModel>>

    fun getTaskByID(taskID: Int): Flow<TaskModel>

    suspend fun addTask(task: TaskModel)

    suspend fun editTask(task: TaskModel)

    suspend fun deleteTask(task: TaskModel)

}
