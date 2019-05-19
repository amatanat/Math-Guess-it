package de.ma.mathguessit.screens.game


import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        binding.gameViewModel = gameViewModel

        binding.lifecycleOwner = this

        gameViewModel.eventGameFinished.observe(this, Observer { hasFinished ->
            if(hasFinished){
                val currentScore = gameViewModel.score.value ?: 0
                val directions = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController().navigate(directions)
                gameViewModel.onGameFinishedComplete()
            }
        })

        return binding.root
    }
}
