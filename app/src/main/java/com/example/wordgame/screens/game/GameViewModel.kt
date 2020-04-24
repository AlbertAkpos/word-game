package com.example.wordgame.screens.game

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var word = ""

    var score = 0

    private lateinit var wordList: MutableList<String>


    init {
        resetList()
        nextWord()
    }

    private fun nextWord() {
        if (wordList.isNotEmpty()){
            //select and remove a word from the list
            word = wordList.removeAt(0)
//            updateScoreText()
//            updateWordText()
        }
    }

    private fun onSkip() {
        score--
        nextWord()
    }

    private fun onCorrect() {
        score++
        nextWord()
    }




    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
}