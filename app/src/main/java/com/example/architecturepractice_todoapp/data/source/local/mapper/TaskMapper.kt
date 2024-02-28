package com.example.architecturepractice_todoapp.data.source.local.mapper

import com.example.architecturepractice_todoapp.data.source.local.model.TaskEntity
import com.example.architecturepractice_todoapp.domain.model.TaskModel

fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        title = title,
        description = description,
        priority = priority,
        category = category,
        isDone = isDone,
        id = id
    )
}

fun TaskModel.toTaskEntity(): TaskEntity {
    return TaskEntity(
        title = title,
        description = description,
        priority = priority,
        category = category,
        isDone = isDone,
        id = id
    )
}
