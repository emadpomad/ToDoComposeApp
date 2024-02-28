package com.example.architecturepractice_todoapp.domain.usecase.tasklist

import android.util.Log
import com.example.architecturepractice_todoapp.domain.model.OrderType
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.model.TaskOrder
import com.example.architecturepractice_todoapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(query: String, order: TaskOrder): Flow<List<TaskModel>> {
        return when (order.orderType) {
            OrderType.Ascending -> when (order) {
                is TaskOrder.Category -> taskRepository.getAllTasks().map { tasks ->
                    tasks.sortedBy { task ->
                        task.category
                    }
                }

                is TaskOrder.Priority -> taskRepository.getAllTasks().map { tasks ->
                    tasks.sortedBy { task ->
                        task.priority.scale
                    }
                }

                is TaskOrder.Title -> taskRepository.getAllTasks().map { tasks ->
                    tasks.sortedBy { task ->
                        task.title
                    }
                }
            }

            OrderType.Descending -> when (order) {
                is TaskOrder.Category -> taskRepository.getAllTasks().map { tasks ->
                    tasks.sortedByDescending { task ->
                        task.category
                    }
                }

                is TaskOrder.Priority -> taskRepository.getAllTasks().map { tasks ->
                    tasks.sortedByDescending { task ->
                        task.priority.scale
                    }
                }

                is TaskOrder.Title -> taskRepository.getAllTasks().map { tasks ->
                    tasks.sortedByDescending { task ->
                        task.title
                    }
                }
            }
        }.map { tasks ->
            Log.d("SearchTasks-Debug", tasks.toString())
            tasks.filter { task ->
                task.title.lowercase().contains(query.trim().lowercase()) ||
                        task.description.lowercase().contains(query.trim().lowercase())
            }
        }
    }

}
