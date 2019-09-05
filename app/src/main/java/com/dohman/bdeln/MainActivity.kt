package com.dohman.bdeln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordsCount = getWordsCount()

        btn_test.setOnClickListener { txt_test.text = getWordBy(lineNumber = Random.nextInt(wordsCount)) }
    }

    private fun getWordsCount(): Int {
        val reader = BufferedReader(InputStreamReader(assets.open("words")))
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

    private fun getWordBy(lineNumber: Int): String {
        val reader = BufferedReader(InputStreamReader(assets.open("words")))
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
