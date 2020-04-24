package com.example.wordgame.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int): ViewModel() {
    var score = finalScore

    private val _playAgain = MutableLiveData(false)
    val playAgain: LiveData<Boolean>
        get() = _playAgain

     fun onPlayAgain() {
        _playAgain.value = true
    }

    fun onPlayAgainComplete() {
        _playAgain.value = false
    }
}