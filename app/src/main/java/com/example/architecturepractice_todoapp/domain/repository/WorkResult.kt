package com.example.architecturepractice_todoapp.domain.repository

sealed class WorkResult {
    class Success<T>(val data: T) : WorkResult()
    class Error(val exception: Exception) : WorkResult()
    class Loading : WorkResult()
}
