package com.dohman.bdeln.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dohman.bdeln.R
import com.dohman.bdeln.ui.main.custom.LetterItem
import com.dohman.bdeln.util.randomize
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

        setupOnClickListeners()
        setupLetterRecycler()

        val wordsCount = vm.getWordsCount(applicationContext)
        val word = vm.getWordFrom(lineNumber = wordsCount.randomize(), ctx = applicationContext)

        buildUnderScores(word = word)
    }

    private fun buildUnderScores(word: String) {
        for (char in word) {
            itemAdapter.add(LetterItem(char))
        }

        Toast.makeText(applicationContext, "$word ${word.length}", Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View?) {
        when (view) {
        }
    }

    private fun setupOnClickListeners() {
    }

    private fun setupLetterRecycler() = v_letter_recycler.apply {
        itemAnimator = null
        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        adapter = fastAdapter
    }
}
