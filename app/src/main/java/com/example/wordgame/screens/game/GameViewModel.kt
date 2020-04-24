package com.example.wordgame.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _word = MutableLiveData("")
    val word: LiveData<String>
        get() = _word

    private var _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private var _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinished


    private var _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val timer: CountDownTimer

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    val wordHint = Transformations.map(word) {
        val randomPosition = (1..word.value?.length!!).random()
        "Current word has ${word.value?.length} letters \nThe letter at position $randomPosition " +
                "is ${word.value?.let { it[randomPosition - 1].toUpperCase() }} "
    }

    fun onGameFinished() {
        _eventGameFinished.value = true
    }

    private lateinit var wordList: MutableList<String>


    fun onGameFinishedComplete() {
        _eventGameFinished.value = false
    }

    init {
        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNT_DOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinished()
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }
        }

        timer.start()
    }


    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            //select and remove a word from the list
            _word.value = wordList.removeAt(0)
            return
        }
        resetList()
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

    companion object {
        private const val DONE = 0L

        private const val ONE_SECOND = 1000L

        private const val COUNT_DOWN_TIME = 60_000L
    }


}