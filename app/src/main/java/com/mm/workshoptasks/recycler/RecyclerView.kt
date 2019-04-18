package com.mm.workshoptasks.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

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