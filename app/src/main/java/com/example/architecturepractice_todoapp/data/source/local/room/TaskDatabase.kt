package com.example.architecturepractice_todoapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.architecturepractice_todoapp.data.source.local.model.TaskEntity
import com.example.architecturepractice_todoapp.data.source.local.typeconverter.CategoryTypeConverter
import com.example.architecturepractice_todoapp.data.source.local.typeconverter.PriorityTypeConverter

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    PriorityTypeConverter::class,
    CategoryTypeConverter::class
)
abstract class TaskDatabase:RoomDatabase() {

    abstract val dao : TaskDao

    companion object {
        const val DATABASE_NAME = "task_database"
    }

}
