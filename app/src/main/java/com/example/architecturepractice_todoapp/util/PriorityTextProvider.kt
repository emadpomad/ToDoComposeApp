package com.example.architecturepractice_todoapp.util

import com.example.architecturepractice_todoapp.domain.model.PriorityModel
import com.example.architecturepractice_todoapp.domain.model.PriorityModel.High
import com.example.architecturepractice_todoapp.domain.model.PriorityModel.Low
import com.example.architecturepractice_todoapp.domain.model.PriorityModel.Medium
import com.example.architecturepractice_todoapp.domain.model.PriorityModel.None
import com.example.architecturepractice_todoapp.domain.model.PriorityModel.VeryHigh
import com.example.architecturepractice_todoapp.domain.model.PriorityModel.VeryLow

val PriorityModel.text: String
    get() =
        when (this) {
            VeryHigh -> "Very High"
            High -> "High"
            Medium -> "Medium"
            Low -> "Low"
            VeryLow -> "Very Low"
            None -> "None"
        }
