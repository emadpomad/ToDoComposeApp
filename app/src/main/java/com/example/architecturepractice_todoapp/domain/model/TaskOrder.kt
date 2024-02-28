package com.example.architecturepractice_todoapp.domain.model

sealed class TaskOrder(val orderType: OrderType) {

    class Title(orderType: OrderType) : TaskOrder(orderType)
    class Priority(orderType: OrderType) : TaskOrder(orderType)
    class Category(orderType: OrderType) : TaskOrder(orderType)


}
