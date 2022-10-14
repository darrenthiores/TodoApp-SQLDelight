package com.darrenthiores.todoappcompose.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.todoappcompose.R
import com.darrenthiores.todoappcompose.ui.theme.TodoAppComposeTheme

@Composable
fun TodoInput(
    modifier: Modifier = Modifier,
    label: String,
    singleLine: Boolean,
    currentText: String,
    onTextChange: (String) -> Unit,
    error: Boolean,
    showError: (Boolean) -> Unit
) {
    OutlinedTextField(
        value = currentText,
        onValueChange = { text ->
            if(singleLine) {
                if(text.length <= 32) onTextChange(text)
            } else {
                onTextChange(text)
            }
            showError(false)
             },
        label = { Text(text = label) },
        isError = error,
        trailingIcon = {
            if(error) {
                Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = stringResource(id = R.string.invalid_input),
                    tint = MaterialTheme.colors.error
                )
            }
        },
        maxLines = if(singleLine) 1 else 10,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .testTag(label)
    )
    if(error) {
        Text(
            text = stringResource(id = R.string.invalid_input),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
private fun TodoInputPreview() {
    TodoAppComposeTheme {
        /*TodoInput(
            label = "Title",
            maxLines = 2,
            currentText = "This is test title",
            onTextChange = {  }
        )*/
    }
}