package studio.juno.foreducation.example.runningapp.splash.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import studio.juno.foreducation.example.runningapp.navigation.FeatureFlow
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.home.HomeFeatureFlow
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.race_tracking.RaceTrackingFeatureFlow
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.splash.InitialDestination
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.tools.ToolsResourcesFeatureFlow
import studio.juno.foreducation.example.runningapp.splash.databinding.ActivitySplashBinding

/* */
class SplashActivity : AppCompatActivity() {

    /* */
    private val binding: ActivitySplashBinding
            by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    /* */
    private val defaultInitialDestinationFeatureFlow: FeatureFlow by inject<HomeFeatureFlow>()

    /* */
    private val splashTime: Long = 1_200L

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        manageInitialDestination(intent)
    }

    /**
     *
     */
    override fun onNewIntent(intent: Intent?) {
        manageInitialDestination(intent)
        super.onNewIntent(intent)
    }

    /**
     *
     */
    private fun manageInitialDestination(intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(splashTime)
            getInitialDestinationFeatureFlow(intent).execute(this@SplashActivity)
        }
    }

    /**
     *
     */
    private fun getInitialDestinationFeatureFlow(intent: Intent?): FeatureFlow =
        when (intent?.action) {
            InitialDestination.HOME.toString() -> get<HomeFeatureFlow>()
            InitialDestination.RACE_TRACKING.toString() -> get<RaceTrackingFeatureFlow>()
            else -> defaultInitialDestinationFeatureFlow
        }

}