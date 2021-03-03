package studio.juno.foreducation.example.runningapp.home

import android.content.Context
import studio.juno.foreducation.example.runningapp.home.presentation.HomeActivity
import studio.juno.foreducation.example.runningapp.navigation.extension.navigateTo
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.home.HomeFeatureFlow

/* */
internal class HomeFeatureFlowImpl : HomeFeatureFlow {

    /**
     *
     */
    override fun execute(context: Context) {
        context.navigateTo(HomeActivity::class.java, clearTop = true)
    }

}