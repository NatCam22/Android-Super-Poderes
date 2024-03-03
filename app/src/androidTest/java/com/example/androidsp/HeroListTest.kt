package com.example.androidsp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.androidsp.domain.Hero
import com.example.androidsp.ui.heroDetailScreen.ScaffoldDetail
import com.example.androidsp.ui.heroDetailScreen.StateHeroDetail
import org.junit.Rule
import org.junit.Test

class HeroListTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun whenMenuButtonClickedThenDrawerAppears() {
        /*
        composeRule.setContent {
            HeroDetailScreen(id = 1009148, navigateToHome = {}, navigateToSeries = {}, navigateToDetail = {}, navigateToComics = {})
        }
         */
        with(composeRule) {
            setContent {
                ScaffoldDetail(
                    state = StateHeroDetail.SuccessGetHero(Hero(1009148, "mancito", "", "", false)),
                    id = 1009148,
                    navigateToSeries = {},
                    navigateToComics = {},
                    navigateToDetail = {}
                ) {

                }
            }
            onNodeWithContentDescription("Hero menu button").assertExists()
            onNodeWithText("Series").assertIsNotDisplayed()
            onNodeWithText("Comics").assertIsNotDisplayed()
            onNodeWithText("Home").assertIsNotDisplayed()
            onNode(hasText("Detalle") and hasClickAction()).assertIsNotDisplayed()
            onNodeWithContentDescription("Hero menu button").performClick()
            onNodeWithText("Series").assertIsDisplayed()
            onNodeWithText("Comics").assertIsDisplayed()
            onNodeWithText("Home").assertIsDisplayed()
            onNode(hasText("Detalle") and hasClickAction()).assertIsDisplayed()
        }
    }


}