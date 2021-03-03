package studio.juno.foreducation.example.runningapp.splash

import android.content.Context
import studio.juno.foreducation.example.runningapp.navigation.extension.navigateTo
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.splash.SplashFeatureFlow
import studio.juno.foreducation.example.runningapp.splash.presentation.SplashActivity

/* */
internal class SplashFeatureFlowImpl : SplashFeatureFlow {

    /**
     *
     */
    override fun execute(context: Context) {
        context.navigateTo(SplashActivity::class.java, clearTop = true)
    }

}