package de.ma.mathguessit.screens.score


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import de.ma.mathguessit.R
import de.ma.mathguessit.databinding.FragmentScoreBinding
import timber.log.Timber

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private val args: ScoreFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        Timber.i("onCreate is called")
        binding.playAgainBtn.setOnClickListener {
            findNavController().navigate(R.id.action_score_to_game)
        }

        val score = getString(R.string.total_score) + args.score
        binding.totalScoreTv.text = score

        binding.playAgainBtn.setOnClickListener {
            findNavController().navigate(ScoreFragmentDirections.actionScoreToGame())
        }

        return binding.root
    }

}
