package com.darrenthiores.todoappcompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.darrenthiores.todoappcompose.R
import com.darrenthiores.todoappcompose.ui.theme.TodoAppComposeTheme
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .semantics { contentDescription = "Landing Screen" },
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        LaunchedEffect(true) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }

        Image(painterResource(id = R.drawable.todo_comp), contentDescription = null)
    }
}

@Preview
@Composable
private fun LandingPreview() {
    TodoAppComposeTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            LandingScreen {

            }
        }
    }
}
