package com.example.jogodavelha.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class GameTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var game: Game
    private lateinit var player: Player
    private lateinit var cell: Cell

    @Before
    fun setUp() {
        game = Game("Heider", "Android")
        player = game.player1 ?: Player("Heider", "Android")
        cell = Cell(player)
    }

    @Test
    fun retornaVerdadeiro_QuandoTresXHorizontalLinha1() {
        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = cell

        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()

        assertTrue(hasThreeSameHorizontalCells)
    }

    @Test
    fun retornaVerdadeiro_QuandoTresXHorizontalLinha2() {
        game.cells[1][0] = cell
        game.cells[1][1] = cell
        game.cells[1][2] = cell

        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()

        Assert.assertEquals(true, hasThreeSameHorizontalCells)
    }

    @Test
    fun retornaVerdadeiro_QuandoTresXHorizontalLinha3() {
        game.cells[2][0] = cell
        game.cells[2][1] = cell
        game.cells[2][2] = cell

        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()

        Assert.assertEquals(true, hasThreeSameHorizontalCells)
    }

    @Test
    fun retornaFalso_quandoNaoTemTresXHorizontal() {
        game.cells[2][0] = cell
        game.cells[2][1] = cell
        game.cells[2][1] = cell

        val hasThreeSameHorizontalCells = game.hasThreeSameHorizontalCells()

        Assert.assertEquals(false, hasThreeSameHorizontalCells)
    }

    @Test
    fun retornaVerdadeiro_QuandoTresXVerticalColuna1() {
        game.cells[0][0] = cell
        game.cells[1][0] = cell
        game.cells[2][0] = cell

        Assert.assertEquals(true, game.hasThreeSameVerticalCells())
    }

    @Test
    fun retornaVerdadeiro_QuandoTresXVerticalColuna2() {
        game.cells[0][1] = cell
        game.cells[1][1] = cell
        game.cells[2][1] = cell

        Assert.assertEquals(true, game.hasThreeSameVerticalCells())
    }

    @Test
    fun retornaVerdadeiro_QuandoTresXVerticalColuna3() {
        game.cells[0][2] = cell
        game.cells[1][2] = cell
        game.cells[2][2] = cell

        Assert.assertEquals(true, game.hasThreeSameVerticalCells())
    }

    @Test
    fun retornaFalso_quandoNaoTemTresXVertical() {
        game.cells[0][1] = cell
        game.cells[0][1] = cell
        game.cells[2][1] = cell

        Assert.assertEquals(false, game.hasThreeSameVerticalCells())
    }

    @Test
    fun returnaTrue_quandoTemXDiagonalDaDireita() {
        game.cells[0][2] = cell
        game.cells[1][1] = cell
        game.cells[2][0] = cell

        Assert.assertEquals(true, game.hasThreeSameDiagonalCells())
    }

    @Test
    fun returnaTrue_quandoTemXDiagonalDaEsquerda() {
        game.cells[0][0] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell

        Assert.assertEquals(true, game.hasThreeSameDiagonalCells())
    }

    @Test
    fun returnaFalse_quandoNaoTemXDiagonal() {
        game.cells[1][1] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell

        Assert.assertEquals(false, game.hasThreeSameDiagonalCells())
    }

    @Test

    fun endGameIfHasThreeSameHorizontalCells() {
        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = cell

        val hasGameEnded = game.hasGameEnded()

        Assert.assertEquals(true, hasGameEnded)
    }

    @Test
    fun endGameIfHasThreeSameVerticalCells() {
        game.cells[0][0] = cell
        game.cells[1][0] = cell
        game.cells[2][0] = cell

        val hasGameEnded = game.hasGameEnded()

        Assert.assertEquals(true, hasGameEnded)
    }

    @Test
    fun endGameIfHasThreeSameDiagonalCells() {
        game.cells[0][0] = cell
        game.cells[1][1] = cell
        game.cells[2][2] = cell

        val hasGameEnded = game.hasGameEnded()

        Assert.assertEquals(true, hasGameEnded)
    }

    @Test
    fun endGameIfHasFull(){
        game.cells[0][0] = cell
        game.cells[0][1] = cell
        game.cells[0][2] = cell
        game.cells[1][0] = cell
        game.cells[1][1] = cell
        game.cells[1][2] = cell
        game.cells[2][0] = cell
        game.cells[2][1] = cell
        game.cells[2][2] = cell

        val hasGameEnded = game.hasGameEnded()

        Assert.assertEquals(true, hasGameEnded)
    }
}
