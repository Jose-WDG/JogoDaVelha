package com.example.jogodavelha

import com.example.jogodavelha.models.Player
import com.example.jogodavelha.ui.game.GameActivity
import org.junit.Rule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaDialogInteractions.clickDialogPositiveButton
import com.adevinta.android.barista.interaction.BaristaEditTextInteractions.writeTo
import org.junit.Test

class GameActivityTest {

    @Rule
    @JvmField
    var activityRule: ActivityScenarioRule<GameActivity> = ActivityScenarioRule(GameActivity::class.java)

    private val player1 = Player("José", "x")

    private val player2 = Player("Neto", "o")

    @Test
    fun end_game_when_one_player_wins() {
        writeTo(R.id.et_player1, player1.name)
        writeTo(R.id.et_player2, player2.name)
        clickDialogPositiveButton()


        clickOn(R.id.cell_00)
        clickOn(R.id.cell_10)
        clickOn(R.id.cell_01)
        clickOn(R.id.cell_11)
        clickOn(R.id.cell_02)

        assertDisplayed(R.id.tvWinner)
        assertDisplayed(player1.name)
    }

    @Test
    fun display_same_names_message_if_names_same() {
        writeTo(R.id.et_player1, "José")
        writeTo(R.id.et_player2, "José")


        clickDialogPositiveButton()

        assertDisplayed(R.string.game_dialog_same_names)
    }

    @Test
    fun display_empty_name_message_if_one_name_empty() {
        writeTo(R.id.et_player1, "")
        writeTo(R.id.et_player2, "Neto")

        clickDialogPositiveButton()

        assertDisplayed(R.string.game_dialog_empty_name)
    }

    @Test
    fun close_dialog_after_names_valid() {
        writeTo(R.id.et_player1, "Heider 1")
        writeTo(R.id.et_player2, "Android")

        clickDialogPositiveButton()

        assertNotExist(R.id.player1Layout)
    }
}