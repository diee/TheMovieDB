package com.example.themoviedb

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.themoviedb.app.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailsInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun open_first_movie_should_show_details() {
        composeTestRule.waitUntilTimeout(2000)
        composeTestRule.onNodeWithContentDescription("Avatar: The Way of Water").performClick()
        composeTestRule.waitUntilTimeout(1000)
        composeTestRule.onNodeWithText("Avatar: The Way of Water").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.overview_label)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.release_date_label)
        ).assertExists()
    }
}