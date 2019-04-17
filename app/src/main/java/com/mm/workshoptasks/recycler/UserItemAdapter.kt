package com.mm.workshoptasks.recycler

import com.mm.workshoptasks.R
import com.mm.workshoptasks.User
import kotlinx.android.synthetic.main.item_user.*

data class UserItemAdapter(private val user: User) : ItemAdapter(R.layout.item_user) {

    override fun setupView() {
        userTextView.text = user.fullName
    }
}