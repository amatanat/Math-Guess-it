package de.ma.mathguessit

import android.app.Application
import timber.log.Timber

class MathApplication:  Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}