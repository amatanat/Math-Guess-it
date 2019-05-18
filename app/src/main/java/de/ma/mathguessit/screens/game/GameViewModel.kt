package de.ma.mathguessit.screens.game

import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {
    init {
        Timber.i("onCreate is called")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared is called")
    }
}