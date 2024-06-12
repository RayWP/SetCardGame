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
    val cardHistory = MutableLiveData<List<SetCard>>()
    val score = MutableLiveData<Int>(0)

    init {
        generateDeck()
        dealCards()
        cardHistory.value = mutableListOf()
    }

    fun addMoreCards() {
        val newOnscreenDeck = onscreenDeck.value!!.toMutableList()
        for (i in 0 until 3) {
            newOnscreenDeck.add(fullDeck.value!![i])
        }
        fullDeck.value = fullDeck.value!!.subList(3, fullDeck.value!!.size)
        onscreenDeck.value = newOnscreenDeck
        if (!checkAvailableSets()) {
            addMoreCards()
        }
    }

    fun addCardsToHistory(selectedCardsList: List<SetCard>) {
        val newHistory = cardHistory.value!!.toMutableList()
        newHistory.addAll(selectedCardsList)
        cardHistory.value = newHistory
    }

    fun dealCards() {
        val newOnscreenDeck = mutableListOf<SetCard>()
        for (i in 0 until 12) {
            newOnscreenDeck.add(fullDeck.value!![i])
        }
        fullDeck.value = fullDeck.value!!.subList(12, fullDeck.value!!.size)
        onscreenDeck.value = newOnscreenDeck

        if (!checkAvailableSets()) {
            addMoreCards()
        }
    }

    fun checkAvailableSets(): Boolean {
        val onscreenDeckList = onscreenDeck.value!!
        for (i in 0 until onscreenDeckList.size) {
            for (j in i + 1 until onscreenDeckList.size) {
                for (k in j + 1 until onscreenDeckList.size) {
                    if (checkSet(
                            listOf(
                                onscreenDeckList[i], onscreenDeckList[j], onscreenDeckList[k]
                            )
                        )
                    ) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun generateDeck() {
        val newDeck = mutableListOf<SetCard>()
        for (number in SetCardNumber.entries) {
            for (shape in SetCardShape.entries) {
                for (shading in SetCardShading.entries) {
                    for (color in SetCardColor.entries) {
                        newDeck.add(SetCard(false, number, color, shape, shading))
                    }
                }
            }
        }
        newDeck.shuffle()
        fullDeck.value = newDeck
    }

    fun checkSet(selectedCardsList: List<SetCard>): Boolean {

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

    fun resetGame() {
        generateDeck()
        dealCards()
        cardHistory.value = mutableListOf()
        score.value = 0
    }

}