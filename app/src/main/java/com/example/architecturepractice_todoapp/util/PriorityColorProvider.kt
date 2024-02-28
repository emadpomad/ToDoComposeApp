package com.example.architecturepractice_todoapp.util

import androidx.compose.ui.graphics.Color
import com.example.architecturepractice_todoapp.domain.model.PriorityModel
import com.example.architecturepractice_todoapp.ui.theme.highPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.lowPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.mediumPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.nonePriorityColor
import com.example.architecturepractice_todoapp.ui.theme.veryHighPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.veryLowPriorityColor


val PriorityModel.color
    get() =
        when (this) {
            PriorityModel.VeryHigh -> veryHighPriorityColor
            PriorityModel.High -> highPriorityColor
            PriorityModel.Medium -> mediumPriorityColor
            PriorityModel.Low -> lowPriorityColor
            PriorityModel.VeryLow -> veryLowPriorityColor
            PriorityModel.None -> Color.White
        }
