package studio.juno.foreducation.example.runningapp

import android.app.Application
import studio.juno.foreducation.example.runningapp.di.initKoin

/* */
class RunningAppApplication : Application() {

    /**
     *
     */
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

}