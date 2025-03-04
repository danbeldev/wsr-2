package com.example.wsr2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.wsr2.presentation.screens.LoginScreen
import org.junit.Rule
import org.junit.Test

class DialogTest {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun showDialog1() {
        compose.setContent {
            LoginScreen(
                onError = { _, _ ->
                    "Invalid email"
                }
            )
        }

        compose.onNodeWithTag("login").performClick()

        compose.onNodeWithText("Invalid email").assertIsDisplayed()
    }

    @Test
    fun showDialog2() {
        compose.setContent {
            LoginScreen(
                onError = { _, _ ->
                    "Invalid password"
                }
            )
        }

        compose.onNodeWithTag("login").performClick()

        compose.onNodeWithText("Invalid password").assertIsDisplayed()
    }
}