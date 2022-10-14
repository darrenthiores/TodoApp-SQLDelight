package com.darrenthiores.todoappcompose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.darrenthiores.core.model.presenter.Todo
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.todoappcompose.R

@Composable
fun TodoItemList(
    modifier: Modifier = Modifier,
    list: List<Todo>,
    onChecked: (Todo, Boolean) -> Unit,
    onTodoClicked: (Todo) -> Unit,
    onTodoSwiped: (Todo) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            items = list,
            key = { _, todo -> todo.id }
        ) { index, todo ->
            if(index != 0) {
                Divider(Modifier.padding(horizontal = 8.dp))
            }
            TodoItem(
                todo = todo,
                onChecked = { newValue -> onChecked(todo, newValue) },
                modifier = Modifier.clickable(
                    onClickLabel = stringResource(id = R.string.list_cl)
                ) {
                    onTodoClicked(todo)
                },
                onSwiped = { onTodoSwiped(todo) }
            )
        }
    }
}