package com.example.architecturepractice_todoapp.data.repository

import com.example.architecturepractice_todoapp.data.source.local.LocalDataSource
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultTaskRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    TaskRepository {
    override fun getAllTasks(): Flow<List<TaskModel>> =
        localDataSource.getAllTasks()


    override fun getTaskByID(taskID: Int): Flow<TaskModel> = localDataSource.getTaskByID(taskID)

    override suspend fun addTask(task: TaskModel) {
        localDataSource.addTask(task)
    }

    override suspend fun editTask(task: TaskModel) {
        localDataSource.addTask(task)
    }

    override suspend fun deleteTask(task: TaskModel) {
        localDataSource.deleteTask(task)
    }
}
