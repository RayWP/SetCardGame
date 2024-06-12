package com.r76127011.setcardgame.RVAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.r76127011.setcardgame.R
import com.r76127011.setcardgame.databinding.CardHistoryItemBinding
import com.r76127011.setcardgame.databinding.FragmentItemBinding
import com.r76127011.setcardgame.model.SetCard

class MyCardHistoryRecyclerViewAdapter(
) : RecyclerView.Adapter<MyCardHistoryRecyclerViewAdapter.ViewHolder>() {

    constructor(values: List<SetCard>) : this(
    ) {
        this.values = values
    }

    var values: List<SetCard> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.binding.cardModel = item
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) { }

}