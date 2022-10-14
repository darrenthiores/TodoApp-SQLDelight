package com.darrenthiores.todoappcompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.darrenthiores.todoappcompose.ui.theme.TodoAppComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.concurrent.schedule

class TodoNavHostTest {

    /*
    ========= note =========
    to make sure the test running well, please delete the app then run the test or run the test
    in the first try.
     */

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: NavHostController

    @Before
    fun setupRallyNavHost() {

        composeTestRule.setContent {
            navController = rememberNavController()
            TodoAppComposeTheme {
                TodoNavHost(navController = navController)
            }
        }

    }

    @Test
    fun landing_screen_exist() {

        composeTestRule.onNodeWithContentDescription("Landing Screen")
            .assertIsDisplayed()

    }

    @Test
    fun home_screen_exist() {

        asyncTimer()

        composeTestRule.onNodeWithContentDescription("Home Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Add Todo", useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Filter", useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Todo", useUnmergedTree = true)
            .assertIsDisplayed()

    }

    @Test
    fun navigate_to_add_screen() {

        asyncTimer()

        composeTestRule.onNodeWithContentDescription("Add Todo", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Detail Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Add Todo", useUnmergedTree = true)
            .assertIsDisplayed()

    }

    @Test
    fun add_todo() {

        val title = "Hello World"

        asyncTimer()

        composeTestRule.onNodeWithContentDescription("Add Todo", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Detail Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Add Todo", useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("Title", useUnmergedTree = true)
            .assertIsDisplayed()
            .performTextInput(title)

        composeTestRule.onNodeWithTag("Description", useUnmergedTree = true)
            .assertIsDisplayed()
            .performTextInput("Hehehe")

        composeTestRule.onNodeWithText("Add", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Home Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()

    }

    @Test
    fun filter_is_applied() {

        val title = "This is sample 3"
        asyncTimer()

        composeTestRule.onNodeWithContentDescription("Filter", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText("Uncompleted", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Filter", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText("Default", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("$title checkbox", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Filter", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText("Completed", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()

    }

    @Test
    fun navigate_to_edit_screen() {

        val title = "This is sample 3"
        asyncTimer()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Detail Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Edit Todo", useUnmergedTree = true)
            .assertIsDisplayed()

    }

    @Test
    fun update_todo() {
        val title = "This is sample 2"
        val additionalTitle = " (?)"
        val newTitle = title + additionalTitle

        asyncTimer()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Detail Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Edit Todo", useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()
            .performTextInput(additionalTitle)

        composeTestRule.onNodeWithText("Save", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Home Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(newTitle, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun delete_todo() {
        val title = "This is sample 1"

        asyncTimer()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Detail Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Edit Todo", useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Delete", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Home Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertDoesNotExist()
    }

    @Test
    fun add_todo_error_exist() {
        asyncTimer()

        composeTestRule.onNodeWithContentDescription("Add Todo", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Detail Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Add Todo", useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Add", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText("Please Fill This Form", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun detail_header_is_working() {
        val title = "This is sample 3"
        asyncTimer()

        composeTestRule.onNodeWithText(title, useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Detail Screen")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Back", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithContentDescription("Home Screen")
            .assertIsDisplayed()
    }

    private fun asyncTimer (delay: Long = 6000) {
        AsyncTimer.start (delay)
        composeTestRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
    }

    object AsyncTimer {
        var expired = false
        fun start(delay: Long = 1000){
            expired = false
            Timer().schedule(delay) {
                expired = true
            }
        }
    }

}