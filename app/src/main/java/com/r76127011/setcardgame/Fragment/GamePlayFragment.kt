package com.r76127011.setcardgame.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.r76127011.setcardgame.RVAdapter.GamePlayRecyclerViewAdapter
import com.r76127011.setcardgame.ViewModel.GameViewModel
import com.r76127011.setcardgame.component.GridSpacingItemDecoration
import com.r76127011.setcardgame.databinding.FragmentGamePlayBinding

class GamePlayFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    private lateinit var gamePlayAdapter: GamePlayRecyclerViewAdapter
    private lateinit var binding: FragmentGamePlayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamePlayBinding.inflate(inflater, container, false)
        initRecyclerView()

        val scoreText: TextView = binding.score

        val cardLeftText: TextView = binding.cardsLeft
        cardLeftText.text = viewModel.fullDeck.value!!.size.toString()

        val moreCardButton: Button = binding.moreCardButton
        moreCardButton.setOnClickListener {
            if(viewModel.fullDeck.value!!.size == 0) {
                Toast.makeText(context, "No more cards to draw", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addMoreCards()
                gamePlayAdapter.values = viewModel.onscreenDeck.value!!
                cardLeftText.text = viewModel.fullDeck.value!!.size.toString()
            }
        }

        val submitCardButton: Button = binding.submitButton
        submitCardButton.setOnClickListener {
            val selectedCardsList = gamePlayAdapter.selectedCards
            if (selectedCardsList.size != 3) {
                Toast.makeText(context, "Please select 3 cards", Toast.LENGTH_SHORT).show()
            } else {
                if (viewModel.checkSet(selectedCardsList)) {
                    Toast.makeText(context, "Correct! You found a set", Toast.LENGTH_SHORT).show()
                    for (card in selectedCardsList) {
                        viewModel.onscreenDeck.value = viewModel.onscreenDeck.value!!.filter { it != card }
                    }
                    gamePlayAdapter.values = viewModel.onscreenDeck.value!!

                    // update cards left
                    cardLeftText.text = viewModel.fullDeck.value!!.size.toString()

                    // set score
                    viewModel.score.value = viewModel.score.value?.plus(1)
                    scoreText.text = viewModel.score.value.toString()
                } else {
                    Toast.makeText(context, "Incorrect! Not a set", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        //give space between item
        binding.recyclerView.addItemDecoration(
            GridSpacingItemDecoration(3, 20, true)
        )
        gamePlayAdapter = GamePlayRecyclerViewAdapter(viewModel.onscreenDeck.value!!)
        binding.recyclerView.adapter = gamePlayAdapter
    }

}