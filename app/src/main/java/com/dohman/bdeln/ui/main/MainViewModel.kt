package com.dohman.bdeln.ui.main

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.dohman.bdeln.util.randomize
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var lives: Int = 8
    private var correctGuessCount = 0
    private var word: String = ""

    val getLives: Int
        get() = lives

    val getCorrectGuessCount: Int
        get() = correctGuessCount

    val getWord: String
        get() = word

    fun removeLife() = lives--

    fun updateCorrectGuessCount() = correctGuessCount++

    fun reset() {
        lives = 8
        correctGuessCount = 0

        do {
            word = getWordFrom(lineNumber = getWordsCount(getApplication()).randomize(), ctx = getApplication())
        } while (word.length > 15)
    }

    fun loopTheWordAndGetIndexes(letter: String): List<Int> {
        val successIndexes = mutableListOf<Int>()

        for (index in word.indices) {
            if (word[index].toString() == letter.toLowerCase(Locale.ROOT)) successIndexes.add(index)
        }

        return successIndexes
    }

    private fun getWordsCount(ctx: Context): Int {
        val reader = BufferedReader(InputStreamReader(ctx.assets.open("words")))
        var wordsCount = 0

        try {
            while (reader.readLine() != null) {
                wordsCount++
            }
        } catch (e: IOException) {
            Log.e("Error!", e.toString())
        }

        return wordsCount
    }

    private fun getWordFrom(lineNumber: Int, ctx: Context): String {
        val reader = BufferedReader(InputStreamReader(ctx.assets.open("words")))
        var iterator = 0

        try {
            while (reader.readLine() != null) {
                iterator++

                if (iterator == lineNumber) {
                    return reader.readLine().trim()
                }
            }
        } catch (e: IOException) {
            Log.e("Error!", e.toString())
        }

        return ""
    }
}