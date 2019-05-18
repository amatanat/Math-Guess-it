package de.ma.mathguessit.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
            get() = _score

    private var _task = MutableLiveData<String>()
    val task: LiveData<String>
        get() = _task

    lateinit var taskList: MutableList<String>

    private var _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinished

    init {
        Timber.i("onCreate is called")
        _eventGameFinished.value = false
        _score.value = 0
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
           _eventGameFinished.value = true
        } else{
            _task.value = taskList.removeAt(0)
        }
    }

    fun onCorrect(){
        _score.value = (score.value)?.plus(1)
        nextTask()
    }

    fun onSkip(){
        _score.value = (score.value)?.minus(1)
        nextTask()
    }

    fun onGameFinishedComplete(){
        _eventGameFinished.value = false
    }
}