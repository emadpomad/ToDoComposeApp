package com.example.architecturepractice_todoapp.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.architecturepractice_todoapp.domain.model.CategoryModel
import com.example.architecturepractice_todoapp.domain.model.PriorityModel

@Entity(
    tableName = TaskEntity.TABLE_NAME
)
data class TaskEntity(
    val title: String,
    val description: String,
    val priority: PriorityModel,
    val category: CategoryModel,
    val isDone: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int?
){
    companion object{
        const val TABLE_NAME = "task_table"
    }
}
