package de.ma.mathguessit.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int): ViewModel(){

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private var _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        _score.value = finalScore
    }

    fun playAgain(){
        _eventPlayAgain.value = true
    }

    fun playAgainComplete(){
        _eventPlayAgain.value = false
    }
}