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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.r76127011.setcardgame.R
import com.r76127011.setcardgame.RVAdapter.GamePlayRecyclerViewAdapter
import com.r76127011.setcardgame.ViewModel.GameViewModel
import com.r76127011.setcardgame.component.GridSpacingItemDecoration
import com.r76127011.setcardgame.databinding.FragmentGamePlayBinding

class GamePlayFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    private lateinit var gamePlayAdapter: GamePlayRecyclerViewAdapter
    private lateinit var binding: FragmentGamePlayBinding
    private lateinit var cardLeftText: TextView
    private lateinit var scoreText: TextView
    private lateinit var moreCardButton: Button
    private lateinit var submitCardButton: Button
    private lateinit var historyButton: Button
    private lateinit var resetButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamePlayBinding.inflate(inflater, container, false)
        initRecyclerView()

        scoreText = binding.score

        cardLeftText = binding.cardsLeft
        cardLeftText.text = viewModel.fullDeck.value!!.size.toString()

        scoreText.text = viewModel.score.value.toString()

        moreCardButton = binding.moreCardButton
        moreCardButton.setOnClickListener {

           drawMoreCard()
        }

        if(!resources.getBoolean(R.bool.isTablet)) {
            // if its not tablet then get history button
            historyButton = binding.cardHistoryButton!!
            historyButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_gamePlayFragment_to_cardHistoryFragment)
            }
        }

        submitCardButton = binding.submitButton
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

                    viewModel.addCardsToHistory(selectedCardsList)

                    gamePlayAdapter.values = viewModel.onscreenDeck.value!!.toMutableList()

                    gamePlayAdapter.clearSelectedCards()

                    // update cards left
                    cardLeftText.text = viewModel.fullDeck.value!!.size.toString()

                    // set score
                    viewModel.score.value = viewModel.score.value?.plus(1)
                    scoreText.text = viewModel.score.value.toString()

                    // draw more card
                    drawMoreCard()
                } else {
                    Toast.makeText(context, "Incorrect! Not a set", Toast.LENGTH_SHORT).show()
                }
            }

            if (viewModel.fullDeck.value!!.size == 0 && viewModel.onscreenDeck.value!!.size == 0) {
                submitCardButton.isEnabled = false
                Toast.makeText(context, "Game Over! Your score is ${viewModel.score.value}", Toast.LENGTH_SHORT).show()
            }
        }

        resetButton = binding.resetButton
        resetButton.setOnClickListener {
            viewModel.resetGame()
            submitCardButton.isEnabled = true
            cardLeftText.text = viewModel.fullDeck.value!!.size.toString()
            scoreText.text = viewModel.score.value.toString()
            gamePlayAdapter.values = viewModel.onscreenDeck.value!!
        }

        return binding.root
    }

    private fun drawMoreCard() {
        if(viewModel.fullDeck.value!!.size == 0) {
            Toast.makeText(context, "No more cards to draw", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.addMoreCards()
            gamePlayAdapter.values = viewModel.onscreenDeck.value!!
            cardLeftText.text = viewModel.fullDeck.value!!.size.toString()
        }
    }


    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        //give space between item
        binding.recyclerView.addItemDecoration(
            GridSpacingItemDecoration(3, 20, true)
        )
        gamePlayAdapter = GamePlayRecyclerViewAdapter(viewModel.onscreenDeck.value!!)
        binding.recyclerView.adapter = gamePlayAdapter
        binding.recyclerView.setItemViewCacheSize(69)  // 69 is the number of cards in the deck
    }

}