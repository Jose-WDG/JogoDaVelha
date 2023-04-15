package com.example.jogodavelha.models

import android.util.Log
import androidx.lifecycle.MutableLiveData

class Game(playerOne: String, playerTwo: String) {

    companion object {
        private val TAG = Game::class.java.simpleName
        private val BOARD_SIZE = 3
    }

    var player1: Player? = Player(playerOne, "x")
    var player2: Player? = Player(playerTwo, "o")
    var currentPlayer = player1
    var cells: Array<Array<Cell?>> = Array(BOARD_SIZE) { arrayOfNulls<Cell>(BOARD_SIZE) }
    var winner: MutableLiveData<Player?> = MutableLiveData()
    val isBoardFull: Boolean
        get() {
            for (row in cells)
                for (cell in row)
                    if (cell == null || cell.isEmpty) return false
            return true
        }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer === player1) player2 else player1
    }

    fun reset() {
        player1 = null
        player2 = null
        currentPlayer = null
        cells = Array(BOARD_SIZE) { arrayOfNulls<Cell>(BOARD_SIZE) }
    }

    private fun areEqual(vararg cells: Cell?): Boolean {
        for (cell in cells) if (cell?.player?.value == null)
            return false

        val comparisonBase = cells[0]

        for (i in 1 until cells.size)
            if (!comparisonBase?.player?.value.equals(cells[i]?.player?.value))
                return false
        return true
    }

    fun hasThreeSameHorizontalCells(): Boolean {
        try {
            for (i in 0 until BOARD_SIZE) {
                if (areEqual(cells[i][0], cells[i][1], cells[i][2])) {
                    return true
                }
            }
        } catch (e: java.lang.NullPointerException) {
            Log.e(TAG, e.message ?: "")
        }
        return false
    }

    fun hasThreeSameVerticalCells(): Boolean {
        try {
            for (i in 0 until BOARD_SIZE) {
                if (areEqual(cells[0][i], cells[1][i], cells[2][i])) {
                    return true
                }
            }
        } catch (e: java.lang.NullPointerException) {
            Log.e(TAG, e.message ?: "")
        }
        return false
    }

    fun hasThreeSameDiagonalCells(): Boolean {
        try {
            for (i in 0 until BOARD_SIZE) {
                if (areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                    areEqual(cells[0][2], cells[1][1], cells[2][0])
                ) {
                    return true
                }
            }
        } catch (e: java.lang.NullPointerException) {
            Log.e(TAG, e.message ?: "")
        }
        return false
    }

    fun hasGameEnded(): Boolean {
        if (hasThreeSameHorizontalCells() ||
            hasThreeSameVerticalCells() ||
            hasThreeSameDiagonalCells()
        ) {
            winner.value = currentPlayer
            return true
        }

        if (isBoardFull) {
            winner.value = null
            return true
        }

        return false
    }
}


