package studio.juno.foreducation.example.runningapp.race_tracking.tracking_location

import android.content.Context
import android.content.Intent
import studio.juno.foreducation.example.runningapp.race_tracking.presentation.RaceTrackingActivity
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationIntentInjector

/* */
internal class TrackingLocationIntentInjectorImpl : TrackingLocationIntentInjector {

    /**
     *
     */
    override fun getIntent(serviceContext: Context): Intent =
        Intent(serviceContext, RaceTrackingActivity::class.java)

}