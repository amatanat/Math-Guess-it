package de.ma.mathguessit.screens.score


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import de.ma.mathguessit.R
import de.ma.mathguessit.databinding.FragmentScoreBinding
import timber.log.Timber

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private val args: ScoreFragmentArgs by navArgs()

    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        Timber.i("onCreateView is called")

        scoreViewModelFactory = ScoreViewModelFactory(args.score)
        scoreViewModel = ViewModelProviders.of(this, scoreViewModelFactory).get(ScoreViewModel::class.java)

        binding.scoreViewModel = scoreViewModel

        scoreViewModel.score.observe(this, Observer { newScore ->
            val score = getString(R.string.total_score) + " $newScore"
            binding.totalScoreTv.text = score
        })

        scoreViewModel.eventPlayAgain.observe(this, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreToGame())
                scoreViewModel.playAgainComplete()
            }
        })

        return binding.root
    }

}
