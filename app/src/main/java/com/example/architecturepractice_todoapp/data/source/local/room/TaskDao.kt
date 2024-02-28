package com.example.architecturepractice_todoapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturepractice_todoapp.data.source.local.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {


    @Query(
        "SELECT * FROM TASK_TABLE"
    )
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query(
        "SELECT * FROM TASK_TABLE WHERE id = :taskID"
    )
    fun getTask(taskID: Int): Flow<TaskEntity>

    @Insert(
        entity = TaskEntity::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun addTask(task: TaskEntity)

    @Delete(
        entity = TaskEntity::class
    )
    suspend fun deleteTask(task: TaskEntity)

}
