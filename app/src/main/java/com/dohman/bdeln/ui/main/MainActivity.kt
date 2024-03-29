package com.dohman.bdeln.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dohman.bdeln.R
import com.dohman.bdeln.ui.main.custom.LetterItem
import com.dohman.bdeln.util.Util
import com.dohman.bdeln.util.isWrongGuess
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var vm: MainViewModel

    private val itemAdapter = ItemAdapter<AbstractItem<*, *>>()
    private val fastAdapter = FastAdapter.with<AbstractItem<*, *>, ItemAdapter<AbstractItem<*, *>>>(itemAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setupLetterRecycler()
        startNewRound()
    }

    private fun startNewRound() {
        vm.resetRound()
        Util.getAlphabetAsArray().forEach { manageTheButton(enable = true, letter = it) }
        buildUnderScores(word = vm.getWord)
        txt_lives.text = "${vm.getLives} chances"

        Toast.makeText(applicationContext, "${vm.getWord} ${vm.getWord.length}", Toast.LENGTH_LONG).show()
    }

    private fun buildUnderScores(word: String) {
        itemAdapter.clear()

        val underscore = LetterItem(isUnderscore = true)

        for (char in word) {
            itemAdapter.add(underscore)
        }
    }

    private fun updateTheViews(resultIndexesFromGuess: List<Int>, letter: String) {
        manageTheButton(enable = false, letter = letter)

        if (resultIndexesFromGuess.isWrongGuess()) {
            vm.removeLife()

            if (vm.isGameOver()) {
                startNewRound()
            } else {
                txt_lives.text = "${vm.getLives} chances"
            }
        } else {
            resultIndexesFromGuess.forEach { index ->
                vm.updateShownLetterCount()
                itemAdapter.remove(index)
                itemAdapter.add(index, LetterItem(letter))
            }

            if (vm.getShownLetterCount == vm.getWord.length) startNewRound()
        }
    }

    private fun manageTheButton(enable: Boolean, letter: String) {
        val button = when (letter) {
            "A" -> btn_a
            "B" -> btn_b
            "C" -> btn_c
            "D" -> btn_d
            "E" -> btn_e
            "F" -> btn_f
            "G" -> btn_g
            "H" -> btn_h
            "I" -> btn_i
            "J" -> btn_j
            "K" -> btn_k
            "L" -> btn_l
            "M" -> btn_m
            "N" -> btn_n
            "O" -> btn_o
            "P" -> btn_p
            "R" -> btn_r
            "S" -> btn_s
            "T" -> btn_t
            "U" -> btn_u
            "V" -> btn_v
            "X" -> btn_x
            "Y" -> btn_y
            "Z" -> btn_z
            "Å" -> btn_aa
            "Ä" -> btn_ae
            else -> btn_oe
        }

        if (enable) {
            button.setOnClickListener(this)
            button.alpha = 1.0f
        } else {
            button.setOnClickListener(null)
            button.alpha = 0.5f
        }
    }

    override fun onClick(view: View?) {
        val pressed = view as? Button
        val pressedLetter = pressed?.text.toString()

        updateTheViews(
            resultIndexesFromGuess = vm.iterateWordForIndexHits(letter = pressedLetter),
            letter = pressedLetter
        )
    }

    private fun setupLetterRecycler() = v_letter_recycler.apply {
        itemAnimator = null
        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        adapter = fastAdapter
    }
}
