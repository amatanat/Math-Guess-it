package de.ma.mathguessit.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {

    companion object {
        // the game is over
        const val GAME_OVER = 0L
        // the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

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

    private var _currentTime =  MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) { time -> DateUtils.formatElapsedTime(time) }

    private var timer: CountDownTimer

    init {
        Timber.i("init is called")
        _eventGameFinished.value = false
        _score.value = 0
        resetList()
        nextTask()

        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                _currentTime.value = GAME_OVER
                _eventGameFinished.value = true
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }

        }

        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared is called")
        timer.cancel()
    }

    private fun resetList() {
        taskList = mutableListOf(
            "3 x 4",
            "4 x 5",
            "5 x 6",
            "6 x 7",
            "11 x 11",
            "11 x 12",
            "14 x 14",
            "16 x 16",
            "21 x 21",
            "31 x 34",
            "2 x 11"
        )
        taskList.shuffle()
    }


    private fun nextTask() {
        if (taskList.isEmpty()){
            resetList()
        }
        _task.value = taskList.removeAt(0)
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