package com.example.wordgame.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.example.wordgame.R
import com.example.wordgame.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {

    private lateinit var scoreViewModel: ScoreViewModel

    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentScoreBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_score, container,
                false)

        val score = arguments?.let { ScoreFragmentArgs.fromBundle(it).score }
        scoreViewModelFactory = score?.let { ScoreViewModelFactory(it) }!!
        scoreViewModel = ViewModelProvider(this, scoreViewModelFactory).get(ScoreViewModel::class.java)


        binding.scoreText.text = scoreViewModel.score.toString()
    return binding.root
    }

}
