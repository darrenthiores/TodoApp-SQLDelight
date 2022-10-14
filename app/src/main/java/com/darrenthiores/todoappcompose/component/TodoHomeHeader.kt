package com.darrenthiores.todoappcompose.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.todoappcompose.R
import com.darrenthiores.todoappcompose.screen.FilterState

/*
@Composable
fun TodoHomeHeader(
    modifier: Modifier = Modifier,
    onDefaultClicked: () -> Unit,
    onCompletedClicked: () -> Unit,
    onUncompletedClicked: () -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    TodoHomeHeader(
        modifier = modifier,
        expanded = isExpanded,
        onIconClicked = { isExpanded = true },
        onDismiss = { isExpanded = false },
        onDefaultClicked = onDefaultClicked,
        onCompletedClicked = onCompletedClicked,
        onUncompletedClicked = onUncompletedClicked
    )
}

@Composable
fun TodoHomeHeader(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onIconClicked: () -> Unit,
    onDismiss: () -> Unit,
    onDefaultClicked: () -> Unit,
    onCompletedClicked: () -> Unit,
    onUncompletedClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp)
            .padding(bottom = 8.dp)
    ) {
        /* opsional -> logo di start
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painterResource(id = R.drawable.todo_comp),
                contentDescription = null
            )
        }
         */
        /*Image( if wanna use logo
            painterResource(id = R.drawable.todo_comp),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            alignment = Alignment.CenterStart // logo di start
        )*/
        Text(
            text = "Todo",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        Box {
            IconButton(onClick = onIconClicked) {
                Icon(
                    imageVector = Icons.Filled.Sort,
                    contentDescription = "Filter",
                    modifier = Modifier.padding(all = 8.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismiss,
                modifier = Modifier
                    .width(156.dp)
                    .padding(all = 4.dp)
            ) {
                DropdownMenuItem(onClick = {
                    onDismiss()
                    onDefaultClicked()
                }) {
                    Text(text = "Default")
                }
                Divider()
                DropdownMenuItem(onClick = {
                    onDismiss()
                    onCompletedClicked()
                }) {
                    Text(text = "Completed")
                }
                Divider()
                DropdownMenuItem(onClick = {
                    onDismiss()
                    onUncompletedClicked()
                }) {
                    Text(text = "Uncompleted")
                }
            }
        }
    }
}*/

@Composable
fun FilterButton(
    onDefaultClicked: () -> Unit,
    onCompletedClicked: () -> Unit,
    onUncompletedClicked: () -> Unit,
    filter: FilterState
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    FilterButton(
        expanded = isExpanded,
        filter = filter,
        onIconClicked = { isExpanded = true },
        onDismiss = { isExpanded = false },
        onDefaultClicked = onDefaultClicked,
        onCompletedClicked = onCompletedClicked,
        onUncompletedClicked = onUncompletedClicked
    )
}

@Composable
fun FilterButton(
    expanded: Boolean,
    filter: FilterState,
    onIconClicked: () -> Unit,
    onDismiss: () -> Unit,
    onDefaultClicked: () -> Unit,
    onCompletedClicked: () -> Unit,
    onUncompletedClicked: () -> Unit
) {
    Box {
        IconButton(onClick = onIconClicked) {
            Icon(
                imageVector = Icons.Filled.Sort,
                contentDescription = stringResource(id = R.string.filter_cd),
                modifier = Modifier.padding(all = 8.dp)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss,
            modifier = Modifier
                .width(156.dp)
                .padding(all = 4.dp)
        ) {
            DropdownMenuItem(onClick = {
                onDismiss()
                onDefaultClicked()
            }) {
                Text(
                    text = stringResource(id = R.string.default_text),
                    color = if(filter == FilterState.Default) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
            Divider()
            DropdownMenuItem(onClick = {
                onDismiss()
                onCompletedClicked()
            }) {
                Text(
                    text = stringResource(id = R.string.completed_text),
                    color = if(filter == FilterState.Complete) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
            Divider()
            DropdownMenuItem(onClick = {
                onDismiss()
                onUncompletedClicked()
            }) {
                Text(
                    text = stringResource(id = R.string.uncompleted_text),
                    color = if(filter == FilterState.UnComplete) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

/*
@Preview
@Composable
private fun HeaderPreview() {
    TodoAppComposeTheme {
        TodoHomeHeader(
            onDefaultClicked = {},
            onCompletedClicked = {},
            onUncompletedClicked = {}
        )
    }
}*/