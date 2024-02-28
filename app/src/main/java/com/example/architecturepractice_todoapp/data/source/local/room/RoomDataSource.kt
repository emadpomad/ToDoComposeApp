package com.example.architecturepractice_todoapp.data.source.local.room

import com.example.architecturepractice_todoapp.data.source.local.LocalDataSource
import com.example.architecturepractice_todoapp.data.source.local.mapper.toTaskEntity
import com.example.architecturepractice_todoapp.data.source.local.mapper.toTaskModel
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val roomDao: TaskDao
) : LocalDataSource {
    override fun getAllTasks(): Flow<List<TaskModel>> = roomDao.getAllTasks().map { tasks ->
        tasks.map { it.toTaskModel() }
    }


    override fun getTaskByID(taskID: Int): Flow<TaskModel> = roomDao.getTask(taskID).map { task ->
        task.toTaskModel()
    }

    override suspend fun addTask(task: TaskModel) = roomDao.addTask(task.toTaskEntity())

    override suspend fun deleteTask(task: TaskModel) = roomDao.deleteTask(task.toTaskEntity())
}
