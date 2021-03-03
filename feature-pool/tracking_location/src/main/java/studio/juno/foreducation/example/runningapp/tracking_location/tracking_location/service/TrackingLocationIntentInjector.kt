package studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service

import android.content.Context
import android.content.Intent

/* */
interface TrackingLocationIntentInjector {

    /**
     *
     */
    fun getIntent(serviceContext: Context): Intent

}
