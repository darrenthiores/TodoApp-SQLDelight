package com.darrenthiores.todoappcompose.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.todoappcompose.R
import com.darrenthiores.todoappcompose.ui.theme.TodoAppComposeTheme

@Composable
fun TodoDetailHeader(
    modifier: Modifier = Modifier,
    isEdit: Boolean,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_navigate_cd),
                modifier = Modifier.padding(all = 8.dp)
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 32.dp)
                .align(Alignment.CenterVertically)
                .semantics { heading() },
            text = stringResource(id = if(isEdit) R.string.edit_header else R.string.add_header),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}

@Preview
@Composable
private fun TodoDetailHeaderPreview() {
    TodoAppComposeTheme {
        TodoDetailHeader(isEdit = false, onBackClick = {  })
    }
}