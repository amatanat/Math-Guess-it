package de.ma.mathguessit.screens.game


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import de.ma.mathguessit.R
import de.ma.mathguessit.databinding.FragmentGameBinding
import timber.log.Timber

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        Timber.i("onCreateView is called")
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        updateScoreText()
        updateTaskText()

        binding.nextBtn.setOnClickListener {
            gameViewModel.onCorrect()
            updateScoreText()
            updateTaskText()
        }

        binding.skipBtn.setOnClickListener {
            gameViewModel.onSkip()
            updateScoreText()
            updateTaskText()
        }

        return binding.root
    }


    private fun updateScoreText() {
        binding.scoreCountTv.text = gameViewModel.score.toString()
    }

    private fun updateTaskText() {
        binding.mathGuessTv.text = gameViewModel.task
    }

    private fun finished() {
        val directions = GameFragmentDirections.actionGameToScore(gameViewModel.score)
        findNavController().navigate(directions)
    }


}
