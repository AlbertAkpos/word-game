package com.example.wordgame.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        binding.correctButton.setOnClickListener { gameViewModel.onCorrect() }
        binding.skipButton.setOnClickListener { gameViewModel.onSkip() }
        binding.endGameButton.setOnClickListener {onEndGame()}

        gameViewModel.score.observe(viewLifecycleOwner, Observer {
            binding.scoreText.text = it.toString()
        })

        gameViewModel.word.observe(viewLifecycleOwner, Observer {
            binding.wordText.text = it
        })

        return binding.root
    }

    private fun onEndGame() {
        gameFinished()
    }

    private fun gameFinished(){
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment()
         gameViewModel.score.value.let {
             if (it != null) {
                 action.score = it
             }
         }
        NavHostFragment.findNavController(this).navigate(action)
    }





}
