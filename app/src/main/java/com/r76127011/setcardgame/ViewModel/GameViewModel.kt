package com.r76127011.setcardgame.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r76127011.setcardgame.model.SetCard
import com.r76127011.setcardgame.model.SetCardColor
import com.r76127011.setcardgame.model.SetCardNumber
import com.r76127011.setcardgame.model.SetCardShading
import com.r76127011.setcardgame.model.SetCardShape

class GameViewModel : ViewModel() {


    val fullDeck = MutableLiveData<List<SetCard>>()
    val onscreenDeck = MutableLiveData<List<SetCard>>()
    val cardHistory = MutableLiveData<List<List<SetCard>>>()
    val score = MutableLiveData<Int>(0)

    init {
        generateDeck()
        dealCards()
    }

    fun addMoreCards() {
        val newOnscreenDeck = onscreenDeck.value!!.toMutableList()
        for (i in 0 until 3) {
            newOnscreenDeck.add(fullDeck.value!![i])
        }
        fullDeck.value = fullDeck.value!!.subList(3, fullDeck.value!!.size)
        onscreenDeck.value = newOnscreenDeck
    }

    fun addCardsToHistory(selectedCardsList: MutableList<SetCard>) {
        val newCardHistory = cardHistory.value!!.toMutableList()
        newCardHistory.add(selectedCardsList)
        cardHistory.value = newCardHistory
    }

    fun dealCards() {
        val newOnscreenDeck = mutableListOf<SetCard>()
        for (i in 0 until 12) {
            newOnscreenDeck.add(fullDeck.value!![i])
        }
        fullDeck.value = fullDeck.value!!.subList(12, fullDeck.value!!.size)
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

    fun checkSet(selectedCardsList: MutableList<SetCard>): Boolean {
        if (selectedCardsList.size != 3) {
            return false
        }
        val numberSet = mutableSetOf<SetCardNumber>()
        val shapeSet = mutableSetOf<SetCardShape>()
        val shadingSet = mutableSetOf<SetCardShading>()
        val colorSet = mutableSetOf<SetCardColor>()

        for (card in selectedCardsList) {
            numberSet.add(card.number)
            shapeSet.add(card.shape)
            shadingSet.add(card.shading)
            colorSet.add(card.color)
        }

        return numberSet.size != 2 && shapeSet.size != 2 && shadingSet.size != 2 && colorSet.size != 2
    }

}