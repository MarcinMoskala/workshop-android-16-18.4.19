package com.mm.workshoptasks.recycler

import com.mm.workshoptasks.R
import kotlinx.android.synthetic.main.item_title.*

data class TitleItemAdapter(private val title: String) : ItemAdapter(R.layout.item_title) {

    override fun setupView() {
        textView.text = title
    }
}