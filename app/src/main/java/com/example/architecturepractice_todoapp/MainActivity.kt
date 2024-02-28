package com.example.architecturepractice_todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.architecturepractice_todoapp.data.source.local.mapper.toTaskEntity
import com.example.architecturepractice_todoapp.data.source.local.room.TaskDao
import com.example.architecturepractice_todoapp.domain.model.CategoryModel
import com.example.architecturepractice_todoapp.domain.model.PriorityModel
import com.example.architecturepractice_todoapp.domain.model.TaskModel
import com.example.architecturepractice_todoapp.presentation.task_list_screen.component.TaskListScreen
import com.example.architecturepractice_todoapp.presentation.task_list_screen.viewmodel.TaskListViewModel
import com.example.architecturepractice_todoapp.ui.theme.ArchitecturePracticeToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dao: TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        lifecycleScope.launch {
//            listOf(
//                TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.VeryLow,
//                    category = CategoryModel.Personal,
//                    isDone = false,
//                    id = 1
//                ),
//                TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.VeryHigh,
//                    category = CategoryModel.Personal,
//                    isDone = false,
//                    id = 26
//                ),
//                TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.High,
//                    category = CategoryModel.Personal,
//                    isDone = true,
//                    id = 36
//                ),
//                TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.High,
//                    category = CategoryModel.Personal,
//                    isDone = true,
//                    id = 35
//                ),
//                TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.High,
//                    category = CategoryModel.Personal,
//                    isDone = true,
//                    id = 34
//                ), TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.High,
//                    category = CategoryModel.Personal,
//                    isDone = true,
//                    id = 33
//                ), TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.High,
//                    category = CategoryModel.Personal,
//                    isDone = true,
//                    id = 32
//                ), TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.High,
//                    category = CategoryModel.Personal,
//                    isDone = true,
//                    id = 31
//                ), TaskModel(
//                    title = "Task",
//                    description = "This is my task.",
//                    priority = PriorityModel.High,
//                    category = CategoryModel.Personal,
//                    isDone = true,
//                    id = 30
//                )
//
//
//            ).forEach { dao.addTask(it.toTaskEntity()) }
//        }

        setContent {
            ArchitecturePracticeToDoAppTheme {
                // A surface container using the 'background' color from the theme

                val vm: TaskListViewModel = hiltViewModel()
                val state by vm.state.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.STARTED)

                TaskListScreen(
                    modifier = Modifier.fillMaxSize(),
                    state = state,
                    navController = rememberNavController(),
                    onEvent = { event ->
                        vm.onEvent(event)
                    }
                )


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArchitecturePracticeToDoAppTheme {
        Greeting("Android")
    }
}
