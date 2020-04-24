package com.example.wordgame.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var _word = MutableLiveData("")
    val word: LiveData<String>
        get() = _word

    private var _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private lateinit var wordList: MutableList<String>


    init {
        resetList()
        nextWord()
    }

     private fun nextWord() {
        if (wordList.isNotEmpty()){
            //select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        _score.value = _score.value?.plus(1)
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