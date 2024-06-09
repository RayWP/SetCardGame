package com.r76127011.setcardgame.RVAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.r76127011.setcardgame.Model.SetCard
import com.r76127011.setcardgame.R
import com.r76127011.setcardgame.databinding.FragmentItemBinding

class GamePlayRecyclerViewAdapter (private val values: List<SetCard>) : RecyclerView.Adapter<GamePlayRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflate<FragmentItemBinding>(inflater, R.layout.fragment_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.binding.gameCard.number = item.number.value
        holder.binding.gameCard.color = item.color.value
        holder.binding.gameCard.shading = item.shading.value
        holder.binding.gameCard.shape = item.shape.value

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}