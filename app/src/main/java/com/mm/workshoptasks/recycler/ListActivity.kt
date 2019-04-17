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

class RecyclerAdapter(
    val items: List<ItemAdapter>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].layoutId

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items[position].apply {
            containerView = holder.view
            setupView()
        }
    }
}

abstract class ItemAdapter(@LayoutRes open val layoutId: Int): LayoutContainer {
    override var containerView: View? = null
    abstract fun setupView()
}

class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view)