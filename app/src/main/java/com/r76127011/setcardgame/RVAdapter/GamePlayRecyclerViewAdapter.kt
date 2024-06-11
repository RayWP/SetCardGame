package com.r76127011.setcardgame.RVAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.r76127011.setcardgame.model.SetCard
import com.r76127011.setcardgame.R
import com.r76127011.setcardgame.databinding.FragmentItemBinding

class GamePlayRecyclerViewAdapter () : RecyclerView.Adapter<GamePlayRecyclerViewAdapter.ViewHolder>() {

    constructor(values: List<SetCard>) : this() {
        this.values = values
    }

    private var numOfSelectedCard = 0

    val selectedCards: MutableList<SetCard> = mutableListOf()

    var values: List<SetCard> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflate<FragmentItemBinding>(inflater, R.layout.fragment_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.binding.cardModel = item
        holder.binding.root.isSelected = false // reset the selected state of the card

        holder.binding.root.setOnClickListener() {
            if (!it.isSelected) {
                if (numOfSelectedCard == 3) {
                    Toast.makeText(holder.binding.root.context, "Can't select more than 3 cards", Toast.LENGTH_SHORT).show()
                } else {
                    numOfSelectedCard++
                    it.isSelected = !it.isSelected
                    selectedCards.add(item)
                }
            } else {
                numOfSelectedCard--
                it.isSelected = !it.isSelected
                selectedCards.remove(item)
            }
        }

        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}