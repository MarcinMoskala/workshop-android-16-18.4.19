package com.mm.workshoptasks.presentation.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mm.workshoptasks.R
import com.mm.workshoptasks.model.User
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