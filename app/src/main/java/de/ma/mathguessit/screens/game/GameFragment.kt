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
    private var score = 0
    private var task = ""
    private lateinit var taskList: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        Timber.i("onCreateView is called")
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        resetList()
        nextTask()

        binding.nextBtn.setOnClickListener {
            score++
            nextTask()
        }

        binding.skipBtn.setOnClickListener {
            score--
            nextTask()
        }

        return binding.root
    }


    private fun nextTask() {
        if (taskList.isEmpty()){
            finished()
        } else{
            task = taskList.removeAt(0)
        }
        updateTaskText()
        updateScoreText()
    }

    private fun updateScoreText() {
        binding.scoreCountTv.text = score.toString()
    }

    private fun updateTaskText() {
        binding.mathGuessTv.text = task
    }

    private fun finished() {
        val directions = GameFragmentDirections.actionGameToScore(score)
        findNavController().navigate(directions)
    }

    private fun resetList() {
        taskList = mutableListOf(
            "3x4",
            "4x5",
            "5x6",
            "6x7",
            "11x11",
            "11x12",
            "14x14",
            "16x16",
            "21x21",
            "31x34",
            "2x11"
        )
        taskList.shuffle()
    }
}
