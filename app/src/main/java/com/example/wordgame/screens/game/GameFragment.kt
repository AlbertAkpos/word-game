package com.example.wordgame.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment

import com.example.wordgame.R
import com.example.wordgame.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {


    private lateinit var binding: FragmentGameBinding

    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        binding.correctButton.setOnClickListener{onCorrect()}
        binding.skipButton.setOnClickListener{ onSkip() }
        binding.endGameButton.setOnClickListener {onEndGame()}

        updateScoreText()
        updateWordText()

        return binding.root
    }

    private fun onEndGame() {
        gameFinished()
    }

    private fun gameFinished(){
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment()
        action.score = gameViewModel.score
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun updateWordText() {
//        binding.wordText.text = word
    }

    private fun updateScoreText() {
//        binding.scoreText.text = score.toString()
    }

    private fun onSkip() {
//        score--
//        nextWord()
    }

    private fun onCorrect() {
//        score++
//        nextWord()
    }



}
