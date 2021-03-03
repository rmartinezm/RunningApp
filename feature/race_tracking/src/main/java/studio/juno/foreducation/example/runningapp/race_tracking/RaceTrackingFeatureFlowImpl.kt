package studio.juno.foreducation.example.runningapp.race_tracking

import android.content.Context
import studio.juno.foreducation.example.runningapp.race_tracking.presentation.RaceTrackingActivity
import studio.juno.foreducation.example.runningapp.navigation.extension.navigateTo
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.race_tracking.RaceTrackingFeatureFlow

/* */
internal class RaceTrackingFeatureFlowImpl : RaceTrackingFeatureFlow {

    /**
     *
     */
    override fun execute(context: Context) {
        context.navigateTo(RaceTrackingActivity::class.java, clearTop = false)
    }

}