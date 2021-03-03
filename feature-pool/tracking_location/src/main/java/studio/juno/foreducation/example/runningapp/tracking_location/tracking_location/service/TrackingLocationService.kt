package studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Looper
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import studio.juno.foreducation.example.runningapp.extension.empty
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.splash.InitialDestination
import studio.juno.foreducation.example.runningapp.notification.getNotificationManager
import studio.juno.foreducation.example.runningapp.permissions.pool_permission.areBackgroundLocationPermissionsAccepted
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.TrackingLocationAction
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationValues._actionTakenLiveData
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationValues._timeRunningInSecondsLiveData
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationValues.actionTakenLiveData
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationValues.timeRunningInSecondsLiveData

/* */
class TrackingLocationService : LifecycleService() {

    /* */
    private val trackingLocationNotificationManager
            by lazy { TrackingLocationNotificationManager(this) }

    /* */
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    /**
     *
     */
    override fun onCreate() {
        super.onCreate()
        TrackingLocationValues.setupInitialValues()
        setupFusedLocationProviderClient()
        setupIsTrackingObserver()
    }

    /**
     *
     */
    private fun setupFusedLocationProviderClient() {
        @SuppressLint("VisibleForTests")
        fusedLocationProviderClient = FusedLocationProviderClient(this)
    }

    /**
     *
     */
    private fun setupIsTrackingObserver() {
        actionTakenLiveData.observe(this, {
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (it) {
                TrackingLocationAction.START ->
                    onActionStart()
                TrackingLocationAction.RESUME ->
                    onActionResume()
                TrackingLocationAction.STOP ->
                    onActionStop()
            }
        })
    }

    /**
     *
     */
    private fun onActionStart() {
        startLocationTracking()
        startForegroundService()
        _actionTakenLiveData.postValue(TrackingLocationAction.RESUME)
    }

    /**
     *
     */
    private fun onActionResume() {
        TrackingLocationPolylineManager.addEmptyPolyline()
        resumeTimeRunningCount()
        observeTimeRunningInSeconds()
    }

    /**
     *
     */
    private fun onActionStop() {
        stopLocationTracking()
        stopForegroundService()
    }

    /**
     *
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            val action = TrackingLocationAction.valueOf(it.action ?: String.empty)
            _actionTakenLiveData.postValue(action)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     *
     */
    @SuppressLint("MissingPermission")
    private fun startLocationTracking() {
        if (areBackgroundLocationPermissionsAccepted()) {
            val request: LocationRequest = createLocationRequest()
            fusedLocationProviderClient
                .requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        }
    }

    /* */
    private fun createLocationRequest(): LocationRequest {
        val locationInterval = 5_000L
        val fastestLocationInterval = 2_000L
        return LocationRequest().apply {
            interval = locationInterval
            fastestInterval = fastestLocationInterval
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    /**
     *
     */
    private fun stopLocationTracking() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    /* */
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)
            if (actionTakenLiveData.value == TrackingLocationAction.RESUME) {
                result?.locations?.let { locations ->
                    locations.forEach {
                        TrackingLocationPolylineManager.addPathPoint(it)
                    }
                }
            }
        }
    }

    /**
     *
     */
    private fun startForegroundService() {
        val notificationId = trackingLocationNotificationManager.foregroundNotificationId
        val notification = trackingLocationNotificationManager.getNotification(
            contentText = "00:00:00",
            pendingIntent = getPendingIntentOrNull()
        )
        startForeground(notificationId, notification)
    }

    /**
     *
     */
    private fun stopForegroundService() {
        stopForeground(true)
        stopSelf()
    }

    /**
     *
     */
    private fun observeTimeRunningInSeconds() {
        timeRunningInSecondsLiveData.observe(this, ::updateNotificationBySeconds)
    }

    /**
     *
     */
    private fun updateNotificationBySeconds(seconds: Long) {
        trackingLocationNotificationManager.apply {
            // TODO: Setup correct string value
            val contentText: String = "00:00:$seconds"
            val notification = getNotification(contentText, getPendingIntentOrNull())
            getNotificationManager().notify(foregroundNotificationId, notification)
        }
    }

    // TIMER MANAGER

    /**
     *
     */
    private fun resumeTimeRunningCount() = lifecycleScope.launch(Dispatchers.Main) {
        while (actionTakenLiveData.value == TrackingLocationAction.RESUME) {
            delay(1_000L)
            timeRunningInSecondsLiveData.value?.let {
                _timeRunningInSecondsLiveData.postValue(it.inc())
            }
        }
    }

    /**
     *
     */
    private fun getPendingIntentOrNull(): PendingIntent? =
        try {
            val intentInjector = get<TrackingLocationIntentInjector>()
            val intent = intentInjector.getIntent(this).apply {
                action = InitialDestination.RACE_TRACKING.toString()
            }
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        } catch (exception: Exception) {
            null
        }

}