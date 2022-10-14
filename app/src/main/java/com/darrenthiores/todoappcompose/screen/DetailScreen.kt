package com.darrenthiores.todoappcompose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.core.model.presenter.Todo
import com.darrenthiores.todoappcompose.R
import com.darrenthiores.todoappcompose.component.TodoDetailHeader
import com.darrenthiores.todoappcompose.component.TodoInput
import com.darrenthiores.todoappcompose.ui.theme.TodoAppComposeTheme
import com.darrenthiores.todoappcompose.viewModel.DetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(
    todo: Todo? = null,
    onBackClick: () -> Unit,
) {
    val viewModel = getViewModel<DetailViewModel>()

    val (titleText, setTitle) = remember {
        if(todo == null) mutableStateOf("") else mutableStateOf(todo.title)
    }
    val (descriptionText, setDescription) = remember {
        if(todo == null) mutableStateOf("") else mutableStateOf(todo.description)
    }
    val (error, showError) = remember {
        mutableStateOf(false)
    }

    val rememberTodo = rememberSaveable {
        mutableStateOf(todo)
    }
    val rememberIsEdit = rememberSaveable {
        mutableStateOf(todo != null)
    }

    Scaffold(
        topBar = {
            TodoDetailHeader(
                isEdit = rememberIsEdit.value,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        DetailContent(
            modifier = Modifier
                .padding(paddingValues)
                .semantics { contentDescription = "Detail Screen" }
            ,
            todo = rememberTodo.value,
            titleText = titleText,
            descriptionText = descriptionText,
            error = error,
            showError = showError,
            onTitleTextChange = setTitle,
            onDescriptionTextChange = setDescription,
            onAddTodo = { todo -> viewModel.insert(todo) },
            onUpdateTodo = { todo, title, description -> viewModel.updateTodo(todo, title, description) },
            onDeleteTodo = { todo -> viewModel.deleteTodo(todo.id) },
            onBackClick = onBackClick
        )
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    todo: Todo? = null,
    titleText: String,
    descriptionText: String,
    error: Boolean,
    showError: (Boolean) -> Unit,
    onTitleTextChange: (String) -> Unit,
    onDescriptionTextChange: (String) -> Unit,
    onAddTodo: (Todo) -> Unit,
    onUpdateTodo: (Todo, String, String) -> Unit,
    onDeleteTodo: (Todo) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        TodoInput(
            label = stringResource(id = R.string.input_label_title),
            singleLine = true,
            currentText = titleText,
            onTextChange = onTitleTextChange,
            error = error,
            showError = showError
        )
        TodoInput(
            modifier = Modifier.padding(top = 8.dp),
            label = stringResource(id = R.string.input_label_description),
            singleLine = false,
            currentText = descriptionText,
            onTextChange = onDescriptionTextChange,
            error = false,
            showError = {  }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                if(titleText.isNotEmpty()) {
                    if(todo == null) {
                        onAddTodo(Todo(title = titleText, description = descriptionText))
                    } else {
                        onUpdateTodo(todo, titleText, descriptionText)
                    }
                    onBackClick()
                } else {
                    showError(true)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = if (todo == null) 16.dp else 0.dp)
        ) {
            Text(text = stringResource(id = if(todo == null) R.string.button_add else R.string.button_save))
        }
        if(todo != null) {
            OutlinedButton(
                onClick = {
                    onDeleteTodo(todo)
                    onBackClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                /*colors = ButtonDefaults.buttonColors( // to custom button color
                    backgroundColor = Color.Red,
                    contentColor = MaterialTheme.colors.surface
                )*/
            ) {
                Text(text = stringResource(id = R.string.button_delete))
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    TodoAppComposeTheme {
        DetailContent(
            titleText = "",
            descriptionText = "",
            error = false,
            showError = {},
            onTitleTextChange = {},
            onDescriptionTextChange = {},
            onAddTodo = {},
            onUpdateTodo = {_, _, _ ->  },
            onDeleteTodo = {},
            onBackClick = {}
        )
    }
}