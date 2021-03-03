package studio.juno.foreducation.example.runningapp.race_tracking.presentation

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import studio.juno.foreducation.example.runningapp.extension.haveAtLeastOneElement
import studio.juno.foreducation.example.runningapp.extension.sizeIsAtLeast
import studio.juno.foreducation.example.runningapp.navigation.extension.APPLICATION_DETAILS_SETTINGS_REQUEST_CODE
import studio.juno.foreducation.example.runningapp.navigation.extension.navigateToApplicationDetailsSettings
import studio.juno.foreducation.example.runningapp.permissions.firstPermissionDenied
import studio.juno.foreducation.example.runningapp.permissions.pool_permission.BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE
import studio.juno.foreducation.example.runningapp.permissions.pool_permission.areBackgroundLocationPermissionsAccepted
import studio.juno.foreducation.example.runningapp.permissions.pool_permission.requestForBackgroundLocationPermissions
import studio.juno.foreducation.example.runningapp.permissions.toMapPermissionsResult
import studio.juno.foreducation.example.runningapp.race_tracking.R
import studio.juno.foreducation.example.runningapp.race_tracking.databinding.ActivityRaceTrackingBinding
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.model.Polyline
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.model.Polylines
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.TrackingLocationAction
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationService
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationValues

/* */
class RaceTrackingActivity : MapAppCompatActivity() {

    /* */
    private val binding: ActivityRaceTrackingBinding
            by lazy { ActivityRaceTrackingBinding.inflate(layoutInflater) }

    /* */
    private val actionTakenLiveData: LiveData<TrackingLocationAction>
        get() = TrackingLocationValues.actionTakenLiveData
    private val polylinesLiveData: LiveData<Polylines>
        get() = TrackingLocationValues.polylinesLiveData
    private val timeRunningInSecondsLiveData: LiveData<Long>
        get() = TrackingLocationValues.timeRunningInSecondsLiveData

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launchLocationPermissionsFlowIfIsNeeded()
        setupMapView(binding.mapView, savedInstanceState, ::onMapAssociated)
        setupViews()
        observeTrackingLocationService()
    }

    /**
     *
     */
    override fun onStart() {
        super.onStart()
        setupMapView(binding.mapView, null, ::onMapAssociated)
    }

    /**
     *
     */
    private fun launchLocationPermissionsFlowIfIsNeeded() {
        if (areBackgroundLocationPermissionsAccepted())
            return
        requestForBackgroundLocationPermissions()
    }

    /**
     *
     */
    private fun onMapAssociated() {
        polylinesLiveData.value?.let {
            addAllPolylines(it)
        }
    }

    /**
     *
     */
    private fun setupViews() {
        binding.apply {
            mbStart.setOnClickListener(::onStartButtonClickListener)
            mbPause.setOnClickListener(::onPauseButtonClickListener)
            mbResume.setOnClickListener(::onResumeButtonClickListener)
            mbStop.setOnClickListener(::onStopButtonClickListener)
        }
    }

    /* */
    private fun onStartButtonClickListener(view: View) {
        sendCommandToTrackingLocationService(TrackingLocationAction.START)
    }

    /* */
    private fun onPauseButtonClickListener(view: View) {
        sendCommandToTrackingLocationService(TrackingLocationAction.PAUSE)
    }

    /* */
    private fun onResumeButtonClickListener(view: View) {
        sendCommandToTrackingLocationService(TrackingLocationAction.RESUME)
    }

    /* */
    private fun onStopButtonClickListener(view: View) {
        sendCommandToTrackingLocationService(TrackingLocationAction.STOP)
    }

    /**
     *
     */
    private fun observeTrackingLocationService() {
        actionTakenLiveData.observe(this, getActionTakenObserver())
        polylinesLiveData.observe(this, getPolylinesObserver())
        timeRunningInSecondsLiveData.observe(this, getTimeRunningInSecondsObserver())
    }

    /* */
    private fun getActionTakenObserver() = Observer<TrackingLocationAction> {
        when (it) {
            TrackingLocationAction.INITIALIZE ->
                setupViewsAsInitialize()
            TrackingLocationAction.START,
            TrackingLocationAction.RESUME ->
                setupViewsAsAvailableToPause()
            TrackingLocationAction.PAUSE ->
                setupViewsAsPause()
            TrackingLocationAction.STOP ->
                setupViewsAsStop()
        }
    }

    /* */
    private fun setupViewsAsInitialize() {
        binding.apply {
            mbStart.visibility = View.VISIBLE
            mbPause.visibility = View.GONE
            llOnPause.visibility = View.GONE
        }
    }

    /* */
    private fun setupViewsAsAvailableToPause() {
        binding.apply {
            mbStart.visibility = View.GONE
            mbPause.visibility = View.VISIBLE
            llOnPause.visibility = View.GONE
        }
    }

    /* */
    private fun setupViewsAsPause() {
        binding.apply {
            mbStart.visibility = View.GONE
            mbPause.visibility = View.GONE
            llOnPause.visibility = View.VISIBLE
        }
    }

    /* */
    private fun setupViewsAsStop() {
        binding.apply {
            mbStart.visibility = View.GONE
            mbPause.visibility = View.GONE
            llOnPause.visibility = View.GONE
        }
    }

    /* */
    private fun getPolylinesObserver() = Observer<Polylines> {
        addLatestPolylineToMap(it)
    }

    /* */
    private fun getTimeRunningInSecondsObserver() = Observer<Long> {
        // TODO: Setup correct string value
        val textLabelFromLong = "00:00:$it"
        binding.tvCron.text = textLabelFromLong
    }

    /**
     *
     */
    private fun addLatestPolylineToMap(polylines: List<Polyline>) {
        if (polylines.isEmpty()) return
        val lastPolyline = polylines.last()
        when {
            lastPolyline.sizeIsAtLeast(2) -> {
                val preLastLatLng = lastPolyline[lastPolyline.size - 2]
                val lastLatLng = lastPolyline.last()
                val polylineOptions = getBasePolylineOptions()
                    .add(preLastLatLng)
                    .add(lastLatLng)
                googleMap?.addPolyline(polylineOptions)
                moveCameraToLatLnt(lastLatLng)
            }
            lastPolyline.haveAtLeastOneElement() ->
                moveCameraToLatLnt(lastPolyline.last())
        }
    }

    /**
     *
     */
    private fun addAllPolylines(polylines: List<Polyline>) {
        if (polylines.isEmpty()) return
        polylines.forEach {
            val polylineOptions = getBasePolylineOptions()
                .addAll(it)
            googleMap?.addPolyline(polylineOptions)
        }
    }

    /* */
    private fun getBasePolylineOptions(): PolylineOptions =
        PolylineOptions()
            .color(Color.RED)
            .width(8f)

    /**
     *
     */
    private fun moveCameraToLatLnt(latLng: LatLng) {
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, 18f)
        )
    }

    /**
     *
     */
    private fun sendCommandToTrackingLocationService(action: TrackingLocationAction) {
        Intent(this, TrackingLocationService::class.java).let {
            it.action = action.toString()
            startService(it)
        }
    }

    /**
     *
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE)
            handleBackgroundLocationPermissionsResult(permissions, grantResults)
    }

    /**
     *
     */
    private fun handleBackgroundLocationPermissionsResult(
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (!areBackgroundLocationPermissionsAccepted()) {
            val mapPermissions = toMapPermissionsResult(permissions, grantResults)
            val permissionDenied: String = mapPermissions.firstPermissionDenied()
            if (shouldShowRequestPermissionRationale(permissionDenied))
                showRequestPermissionRationale(permissionDenied)
            else
                showRequestPermissionRationaleToSettings()
        }
    }

    /* */
    private fun buildBackgroundLocationRequestPermissionRationaleAlertDialog(
        negativeAction: DialogInterface.OnClickListener,
        positiveAction: DialogInterface.OnClickListener
    ): AlertDialog =
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.background_location_request_permission_rationale_title)
            .setMessage(R.string.background_location_request_permission_rationale_message)
            .setNegativeButton(
                R.string.background_location_request_permission_rationale_negative_action,
                negativeAction
            )
            .setPositiveButton(
                R.string.background_location_request_permission_rationale_positive_action,
                positiveAction
            )
            .create().apply { setCancelable(false) }

    /**
     *
     */
    private fun showRequestPermissionRationale(permissionDenied: String) {
        when (permissionDenied) {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION -> {
                buildBackgroundLocationRequestPermissionRationaleAlertDialog(
                    negativeAction = ::onRequestPermissionRationaleNegativeAction,
                    positiveAction = ::onRequestPermissionRationalePositiveAction
                ).show()
            }
            else -> error("Permission: $permissionDenied haven't a request rationale action")
        }
    }

    /**
     *
     */
    private fun onRequestPermissionRationaleNegativeAction(dialog: DialogInterface, which: Int) {
        finish()
    }

    /**
     *
     */
    private fun onRequestPermissionRationalePositiveAction(dialog: DialogInterface, which: Int) {
        requestForBackgroundLocationPermissions()
    }

    /**
     *
     */
    private fun showRequestPermissionRationaleToSettings() {
        buildBackgroundLocationRequestPermissionRationaleAlertDialog(
            negativeAction = ::onRequestPermissionRationaleNegativeAction,
            positiveAction = ::onRequestPermissionRationaleToSettingsPositiveAction
        ).show()
    }

    /**
     *
     */
    private fun onRequestPermissionRationaleToSettingsPositiveAction(
        dialog: DialogInterface,
        which: Int
    ) {
        navigateToApplicationDetailsSettings()
    }

    /**
     *
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == APPLICATION_DETAILS_SETTINGS_REQUEST_CODE)
            handleApplicationDetailsSettingsResult(resultCode, data)
    }

    /**
     *
     */
    private fun handleApplicationDetailsSettingsResult(resultCode: Int, data: Intent?) {
        launchLocationPermissionsFlowIfIsNeeded()
    }

}