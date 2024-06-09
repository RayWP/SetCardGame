package com.r76127011.setcardgame.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r76127011.setcardgame.Model.SetCard
import com.r76127011.setcardgame.Model.SetCardColor
import com.r76127011.setcardgame.Model.SetCardNumber
import com.r76127011.setcardgame.Model.SetCardShading
import com.r76127011.setcardgame.Model.SetCardShape

class GameViewModel : ViewModel() {


    val fullDeck = MutableLiveData<List<SetCard>>()
    val onscreenDeck = MutableLiveData<List<SetCard>>()
    val selectedCards = MutableLiveData<MutableList<SetCard>>()

    init {
        generateDeck()
        dealCards()
    }

    fun dealCards() {
        val newOnscreenDeck = mutableListOf<SetCard>()
        for (i in 0 until 12) {
            newOnscreenDeck.add(fullDeck.value!![i])
        }
        onscreenDeck.value = newOnscreenDeck
    }

    private fun generateDeck() {
        val newDeck = mutableListOf<SetCard>()
        for (number in SetCardNumber.entries) {
            for (shape in SetCardShape.entries) {
                for (shading in SetCardShading.entries) {
                    for (color in SetCardColor.entries)  {
                        newDeck.add(SetCard(number, color, shape, shading))
                    }
                }
            }
        }
        newDeck.shuffle()
        fullDeck.value = newDeck
    }

}