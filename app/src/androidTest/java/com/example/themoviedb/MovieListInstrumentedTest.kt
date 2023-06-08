@file:JvmName("MovieDetailsInstrumentedTestKt")

package com.example.themoviedb

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.themoviedb.app.presentation.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.Timer
import kotlin.concurrent.schedule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MovieListInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * When the database is empty, the app should show the initial loader
     * */
    @Test
    fun show_initial_loader_on_first_launch() {
        composeTestRule.onNodeWithContentDescription("Loading").assertExists()
        composeTestRule.waitUntilTimeout(4000)
        composeTestRule.onNodeWithContentDescription("Loading").assertDoesNotExist()
    }

    @Test
    fun show_movies_list() {
        composeTestRule.onNodeWithContentDescription("Loading").assertDoesNotExist()
        composeTestRule.onAllNodesWithContentDescription("Poster Image").assertCountEquals(6)
    }
}
