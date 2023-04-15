package com.example.jogodavelha

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.adevinta.android.barista.interaction.BaristaDialogInteractions.clickDialogPositiveButton
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import com.example.jogodavelha.models.Player
import com.example.jogodavelha.ui.game.GameActivity
import org.junit.Rule
import org.junit.Test

class GameEndDialogTest {

    @Rule
    @JvmField
    var activityRule: ActivityScenarioRule<GameActivity> = ActivityScenarioRule(GameActivity::class.java)

    private fun givenGameEnded() {
        activityRule.scenario.onActivity {
            it.onGameWinnerChanged(Player("José", "x"))
        }
    }

    @Test
    fun display_winner_when_game_ends() {
        givenGameEnded()

        assertDisplayed(R.id.tvWinner)
    }

    @Test
    fun display_begin_dialog_when_done_clicked() {
        givenGameEnded()

        clickDialogPositiveButton()

        assertNotExist(R.id.tvWinner)

        assertDisplayed(R.id.et_player1)
    }
}