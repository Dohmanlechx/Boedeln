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
import com.dohman.bdeln.util.randomize
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var vm: MainViewModel

    private val itemAdapter = ItemAdapter<AbstractItem<*, *>>()
    private val fastAdapter = FastAdapter.with<AbstractItem<*, *>, ItemAdapter<AbstractItem<*, *>>>(itemAdapter)

    private var wordsCount: Int = 0
    private var word: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProviders.of(this).get(MainViewModel::class.java)

        wordsCount = vm.getWordsCount(applicationContext)
        word = vm.getWordFrom(lineNumber = wordsCount.randomize(), ctx = applicationContext)

        setupOnClickListeners()
        setupLetterRecycler()

        buildUnderScores(word = word)
    }

    private fun buildUnderScores(word: String) {
        for (char in word) {
            itemAdapter.add(LetterItem(char.toString(), isInit = true))
        }

        Toast.makeText(applicationContext, "$word ${word.length}", Toast.LENGTH_LONG).show()
    }

    private fun manageTheUnderScores(letter: String) {
        val affectedIndexes = vm.loopTheWordAndGetIndexes(word = word, letter = letter)

        affectedIndexes.forEach { index ->
            itemAdapter.remove(index)
            itemAdapter.add(index, LetterItem(letter))

        }
    }

    override fun onClick(view: View?) {
        val pressed = view as? Button
        manageTheUnderScores(pressed?.text.toString())
    }

    private fun setupLetterRecycler() = v_letter_recycler.apply {
        itemAnimator = null
        layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        adapter = fastAdapter
    }

    // region OnClickListeners
    private fun setupOnClickListeners() {
        btn_a.setOnClickListener(this)
        btn_b.setOnClickListener(this)
        btn_c.setOnClickListener(this)
        btn_d.setOnClickListener(this)
        btn_e.setOnClickListener(this)
        btn_f.setOnClickListener(this)
        btn_g.setOnClickListener(this)
        btn_h.setOnClickListener(this)
        btn_i.setOnClickListener(this)
        btn_j.setOnClickListener(this)
        btn_k.setOnClickListener(this)
        btn_l.setOnClickListener(this)
        btn_m.setOnClickListener(this)
        btn_n.setOnClickListener(this)
        btn_o.setOnClickListener(this)
        btn_p.setOnClickListener(this)
        btn_r.setOnClickListener(this)
        btn_s.setOnClickListener(this)
        btn_t.setOnClickListener(this)
        btn_u.setOnClickListener(this)
        btn_v.setOnClickListener(this)
        btn_x.setOnClickListener(this)
        btn_y.setOnClickListener(this)
        btn_z.setOnClickListener(this)
        btn_aa.setOnClickListener(this)
        btn_ae.setOnClickListener(this)
        btn_oe.setOnClickListener(this)
    }
    // endregion
}
