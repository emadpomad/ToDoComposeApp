package com.example.architecturepractice_todoapp.domain.usecase.tasklist

import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.domain.repository.TaskRepository
import com.example.architecturepractice_todoapp.domain.model.OrderType
import com.example.architecturepractice_todoapp.domain.model.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(taskOrder: TaskOrder): Flow<List<TaskModel>> {

        return when(taskOrder.orderType){
            OrderType.Ascending -> when(taskOrder){
                is TaskOrder.Category -> taskRepository.getAllTasks().map {tasks->
                    tasks.sortedBy { task->
                        task.category
                    }
                }
                is TaskOrder.Priority -> taskRepository.getAllTasks().map {tasks->
                    tasks.sortedBy { task->
                        task.priority.scale
                    }
                }
                is TaskOrder.Title -> taskRepository.getAllTasks().map {tasks->
                    tasks.sortedBy { task->
                        task.title
                    }
                }
            }
            OrderType.Descending -> when(taskOrder){
                is TaskOrder.Category -> taskRepository.getAllTasks().map {tasks->
                    tasks.sortedByDescending { task->
                        task.category
                    }
                }
                is TaskOrder.Priority -> taskRepository.getAllTasks().map {tasks->
                    tasks.sortedByDescending { task->
                        task.priority.scale
                    }
                }
                is TaskOrder.Title -> taskRepository.getAllTasks().map {tasks->
                    tasks.sortedByDescending { task->
                        task.title
                    }
                }
            }
        }

    }

}
