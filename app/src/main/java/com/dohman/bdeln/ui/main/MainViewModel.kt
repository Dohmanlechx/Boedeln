package com.dohman.bdeln.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainViewModel : ViewModel() {

    fun getWordsCount(ctx: Context): Int {
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

    fun getWordBy(ctx: Context, lineNumber: Int): String {
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