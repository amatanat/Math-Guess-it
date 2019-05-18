package de.ma.mathguessit.screens.game

import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {

    var score = 0
    var task = ""
    lateinit var taskList: MutableList<String>


    init {
        Timber.i("onCreate is called")
        resetList()
        nextTask()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared is called")
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


    private fun nextTask() {
        if (taskList.isEmpty()){
           // finished()
        } else{
            task = taskList.removeAt(0)
        }
    }

    fun onCorrect(){
        score++
        nextTask()
    }

    fun onSkip(){
        score--
        nextTask()
    }
}