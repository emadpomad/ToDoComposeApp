package com.example.architecturepractice_todoapp.domain.model

import androidx.compose.ui.graphics.Color
import com.example.architecturepractice_todoapp.ui.theme.highPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.lowPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.mediumPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.nonePriorityColor
import com.example.architecturepractice_todoapp.ui.theme.veryHighPriorityColor
import com.example.architecturepractice_todoapp.ui.theme.veryLowPriorityColor

enum class PriorityModel(val scale:Int) {
    VeryHigh(5),
    High(4),
    Medium(3),
    Low(2),
    VeryLow(1),
    None(0)

}
