package com.example.architecturepractice_todoapp.data.source.local.typeconverter

import androidx.room.TypeConverter
import com.example.architecturepractice_todoapp.domain.model.CategoryModel

class CategoryTypeConverter {

    @TypeConverter
    fun fromCategory(category: CategoryModel): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(category: String): CategoryModel {
        return CategoryModel.valueOf(category)
    }




}
