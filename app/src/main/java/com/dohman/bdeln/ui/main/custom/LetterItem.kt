package com.dohman.bdeln.ui.main.custom

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dohman.bdeln.R
import com.mikepenz.fastadapter.items.AbstractItem

class LetterItem() : AbstractItem<LetterItem, LetterItem.ViewHolder>() {
    override fun getType(): Int = R.id.adapter_type_letter_item
    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)
    override fun getLayoutRes(): Int = R.layout.item_letter

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context
            get() = itemView.context
    }
}