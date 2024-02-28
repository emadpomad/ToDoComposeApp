package com.example.architecturepractice_todoapp.presentation.task_list_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListSearchAppBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (query: String) -> Unit,
    onSearchActionClicked: (query: String) -> Unit,
    onCloseClicked: () -> Unit
) {

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {

        val focusRequester = remember {
            FocusRequester()
        }


        LaunchedEffect(true) {
            focusRequester.requestFocus()
        }


        TextField(
            modifier = modifier.focusRequester(focusRequester),
            value = searchQuery,
            onValueChange = {
                onSearchQueryChanged(it)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchActionClicked(searchQuery) }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            trailingIcon = {
                IconButton(onClick = { onCloseClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Search",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            placeholder = {
                Text(
                    text = "Search...",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )


        )

    }

}

@Preview
@Composable
fun PreviewTaskListSearchAppBar() {

    TaskListSearchAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        searchQuery = "My Task",
        onSearchQueryChanged = {},
        onSearchActionClicked = {}
    ) {

    }

}
