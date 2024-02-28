package com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.AddEditTaskUiEvent
import com.example.architecturepractice_todoapp.presentation.add_edit_task_screen.AddEditTaskUiState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditTaskScreen(
    modifier: Modifier = Modifier,
    state: AddEditTaskUiState,
    onEvent: (event: AddEditTaskUiEvent) -> Unit,
    navController: NavController
) {


    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AddEditDefaultAppBar(
                modifier = Modifier.fillMaxWidth(),
                onSaveActionClicked = {
                    onEvent(AddEditTaskUiEvent.OnTaskSaved())
                    navController.navigateUp()
                },
                onNavigateUpClicked = {
                    navController.navigateUp()
                },
                onDeleteClicked = {
                    onEvent(AddEditTaskUiEvent.OnTaskDeleted())
                    navController.navigateUp()
                },
                isAdding = state.isAdding
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        }
    ) {


        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
        ) {

            OutlinedTextField(
                value = state.title,
                onValueChange = { title ->
                    onEvent(
                        AddEditTaskUiEvent.OnTitleChanged(title)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                ),
                placeholder = {
                    Text(text = "Title...")
                },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(10),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))


            Surface(
                shape = RoundedCornerShape(10),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            ) {


            }


        }


        LaunchedEffect(key1 = state.error) {
            state.error?.let { error ->
                snackBarHostState.showSnackbar(error.message.toString())
            }
        }


    }


}


@Preview
@Composable
fun PreviewAddEditTaskScreen() {
    AddEditTaskScreen(
        state = AddEditTaskUiState(),
        onEvent = {},
        navController = rememberNavController()
    )
}
