package com.darrenthiores.todoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darrenthiores.core.model.presenter.Todo
import com.darrenthiores.todoappcompose.screen.DetailScreen
import com.darrenthiores.todoappcompose.screen.HomeScreen
import com.darrenthiores.todoappcompose.screen.LandingScreen
import com.darrenthiores.todoappcompose.ui.theme.TodoAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoApp()
        }
    }

}

@Composable
private fun TodoApp() {
    TodoAppComposeTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            /*
            var showLandingScreen by remember {
                mutableStateOf(true)
            }
            if(showLandingScreen) {
                LandingScreen {
                    showLandingScreen = false
                }
            } else {
                TodoNavHost(navController = navController)
            }
            */
            TodoNavHost(navController = navController)
        }
    }
}

@Composable
fun TodoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TodoScreen.Landing.name,
        modifier = modifier
    ) {
        composable(TodoScreen.Landing.name) {
            LandingScreen {
                navController.navigate(TodoScreen.Home.name) {
                    popUpTo(TodoScreen.Landing.name) {
                        inclusive = true
                    }
                }
            }
        }
        composable(TodoScreen.Home.name) {
            HomeScreen(
                onFabClicked = { navController.navigate(TodoScreen.Detail.name) },
                onTodoClicked = { todo -> navigateToUpdateDetail(navController, todo) }
            )
        }
        composable(TodoScreen.Detail.name) {
            DetailScreen {
                navController.navigate(TodoScreen.Home.name) {
                    popUpTo(TodoScreen.Detail.name) {
                        inclusive = true
                    }
                }
            }
        }
        val detailName = TodoScreen.Detail.name
        composable("$detailName/{todo}") {
            val todo = navController.previousBackStackEntry?.arguments?.getParcelable<Todo>("todo")
            DetailScreen(
                todo = todo
            ) {
                navController.navigate(TodoScreen.Home.name) {
                    popUpTo(TodoScreen.Detail.name) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

private fun navigateToUpdateDetail(
    navController: NavController,
    todo: Todo
) {
    navController.currentBackStackEntry?.arguments?.putParcelable("todo", todo)
    navController.navigate("${TodoScreen.Detail.name}/$todo")
}