package com.example.architecturepractice_todoapp.data.source.local.typeconverter

import androidx.room.TypeConverter
import com.example.architecturepractice_todoapp.domain.model.PriorityModel

class PriorityTypeConverter {
    @TypeConverter
    fun fromPriority(priority: PriorityModel): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): PriorityModel {
        return PriorityModel.valueOf(priority)
    }
}
