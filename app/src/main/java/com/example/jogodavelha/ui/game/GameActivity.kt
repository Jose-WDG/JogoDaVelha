package com.example.jogodavelha.ui.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableMap
import androidx.lifecycle.ViewModelProvider
import com.example.jogodavelha.databinding.ActivityGameBinding
import com.example.jogodavelha.models.Player

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        promptForPlayers()
    }

    private fun initOnclick() {
        initOnclick(binding.cell00, 0, 0)
        initOnclick(binding.cell01, 0, 1)
        initOnclick(binding.cell02, 0, 2)
        initOnclick(binding.cell10, 1, 0)
        initOnclick(binding.cell11, 1, 1)
        initOnclick(binding.cell12, 1, 2)
        initOnclick(binding.cell20, 2, 0)
        initOnclick(binding.cell21, 2, 1)
        initOnclick(binding.cell22, 2, 2)
    }

    private fun setTextCell(key: String) {
        when (key) {
            "00" -> {
                binding.cell00.text = gameViewModel.cells[key]
            }
            "01" -> {
                binding.cell01.text = gameViewModel.cells[key]
            }
            "02" -> {
                binding.cell02.text = gameViewModel.cells[key]
            }
            "10" -> {
                binding.cell10.text = gameViewModel.cells[key]
            }
            "11" -> {
                binding.cell11.text = gameViewModel.cells[key]
            }
            "12" -> {
                binding.cell12.text = gameViewModel.cells[key]
            }
            "20" -> {
                binding.cell20.text = gameViewModel.cells[key]
            }
            "21" -> {
                binding.cell21.text = gameViewModel.cells[key]
            }
            "22" -> {
                binding.cell22.text = gameViewModel.cells[key]
            }
        }
    }

    private fun resetFields() {
        binding.cell00.text = ""
        binding.cell01.text = ""
        binding.cell02.text = ""
        binding.cell10.text = ""
        binding.cell11.text = ""
        binding.cell12.text = ""
        binding.cell20.text = ""
        binding.cell21.text = ""
        binding.cell22.text = ""
    }

    private fun initOnclick(cell: TextView, row: Int, colum: Int) {
        cell.toMark(row, colum, gameViewModel)
    }

    // Exibe o dialog solicitando o nome dos jogadores
    fun promptForPlayers() {
        val dialog = GameBeginDialog.newInstance(this)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_BEGIN_DIALOG_TAG)
    }

    // método para setar o jogadores que serão preenchidos pelo dialog
    fun onPlayersSet(player1: String, player2: String) {
        initDataBinding(player1, player2)
    }


    private fun initDataBinding(player1: String, player2: String) {
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        gameViewModel.init(player1, player2)
        initOnclick()
        setUpOnGameEndListener()
    }


    private fun setUpOnGameEndListener() {
        gameViewModel.winner.observe(this) {
            this.onGameWinnerChanged(it)
        }
        gameViewModel.cells.addOnMapChangedCallback(object :
            ObservableMap.OnMapChangedCallback<ObservableArrayMap<String, String>, String, String>() {
            override fun onMapChanged(sender: ObservableArrayMap<String, String>, key: String) {
                setTextCell(key)
            }
        })
    }

    internal fun onGameWinnerChanged(winner: Player?) {
        val winnerName = if (winner == null || winner.name.isEmpty()) NO_WINNER else winner.name
        val dialog = GameEndDialog.newInstance(this, winnerName)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_END_DIALOG_TAG)
        resetFields()
    }

    companion object {
        private val GAME_BEGIN_DIALOG_TAG = "game_dialog_tag"
        private val GAME_END_DIALOG_TAG = "game_end_dialog_tag"
        private val NO_WINNER = "Empate!"
    }
}

fun TextView.toMark(row: Int, colum: Int, viewModel: GameViewModel) {
    setOnClickListener {
        viewModel.onClickedCellAt(row, colum)
    }
}
