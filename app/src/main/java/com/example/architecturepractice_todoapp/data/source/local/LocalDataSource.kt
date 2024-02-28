package com.example.architecturepractice_todoapp.data.source.local

import com.example.architecturepractice_todoapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllTasks(): Flow<List<TaskModel>>
//    fun searchTasks(query: String): Flow<List<TaskModel>>
    fun getTaskByID(taskID: Int): Flow<TaskModel>
    suspend fun addTask(task: TaskModel)
    suspend fun deleteTask(task: TaskModel)

}
