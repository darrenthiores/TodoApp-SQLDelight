package com.darrenthiores.todoappcompose.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.darrenthiores.core.model.presenter.Todo
import com.darrenthiores.todoappcompose.component.FilterButton
import com.darrenthiores.todoappcompose.component.TodoItemList
import com.darrenthiores.todoappcompose.viewModel.HomeViewModel
import org.koin.androidx.compose.getViewModel
import com.darrenthiores.todoappcompose.R

enum class FilterState {
    Default, Complete, UnComplete
}

@Composable
fun HomeScreen(
    onTodoClicked: (Todo) -> Unit,
    onFabClicked: () -> Unit
) {
    val viewModel = getViewModel<HomeViewModel>()

    Scaffold(
        topBar = {
            val filterState = viewModel.filterState.collectAsState()
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.appbar_title),
                        style = MaterialTheme.typography.h4
                    )
                },
                actions = {
                    FilterButton(
                        onDefaultClicked = { viewModel.filterTodo(FilterState.Default) },
                        onCompletedClicked = { viewModel.filterTodo(FilterState.Complete) },
                        onUncompletedClicked = { viewModel.filterTodo(FilterState.UnComplete) },
                        filter = filterState.value
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClicked,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_header)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        val filterState = viewModel.filterState.collectAsState()
        val todosState = viewModel.todos.collectAsState()
        val todos = when(filterState.value) {
            FilterState.Default -> todosState.value
            FilterState.Complete -> todosState.value.filter { it.done }
            FilterState.UnComplete -> todosState.value.filter { !it.done }
        }
        TodoItemList(
            modifier = Modifier
                .padding(padding)
                .semantics { contentDescription = "Home Screen" },
            list = todos,
            onChecked = { todo: Todo, state: Boolean -> viewModel.checklistTodo(todo, state) },
            onTodoClicked = onTodoClicked,
            onTodoSwiped = { todo -> viewModel.deleteTodo(todo.id) }
        )
    }

}