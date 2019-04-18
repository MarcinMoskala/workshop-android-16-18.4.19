package com.mm.workshoptasks.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mm.workshoptasks.R
import com.mm.workshoptasks.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val list = listOf(
            UserItemAdapter(User("Michal", "Allan")),
            TitleItemAdapter("AAAA"),
            UserItemAdapter(User("Marcin", "Moskała")),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("BBBB"),
            TitleItemAdapter("CCCC")
        )
        recyclerView.adapter = RecyclerAdapter(list)
    }
}