package studio.juno.foreducation.example.runningapp.permissions.pool_permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment
import studio.juno.foreducation.example.runningapp.permissions.arePermissionsAlreadyAccepted

/* */
const val BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE: Int = 50

/**
 *
 */
fun Activity.requestForBackgroundLocationPermissions() =
    requestPermissions(backgroundLocationPermissions, BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE)

/* */
val backgroundLocationPermissions: Array<String> = ArrayList<String>().apply {
    add(Manifest.permission.ACCESS_FINE_LOCATION)
    add(Manifest.permission.ACCESS_COARSE_LOCATION)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
}.toTypedArray()

/* */
fun Fragment.areBackgroundLocationPermissionsAccepted(): Boolean =
    arePermissionsAlreadyAccepted(backgroundLocationPermissions)

/* */
fun Context.areBackgroundLocationPermissionsAccepted(): Boolean =
    arePermissionsAlreadyAccepted(backgroundLocationPermissions)
