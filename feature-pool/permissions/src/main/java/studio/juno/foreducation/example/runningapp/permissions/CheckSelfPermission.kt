package studio.juno.foreducation.example.runningapp.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 *
 */
fun Fragment.arePermissionsAlreadyAccepted(requiredPermissions: Iterable<String>): Boolean =
    requireContext().arePermissionsAlreadyAccepted(requiredPermissions)

/**
 *
 */
fun Fragment.arePermissionsAlreadyAccepted(requiredPermissions: Array<String>): Boolean =
    requireContext().arePermissionsAlreadyAccepted(requiredPermissions)

/**
 *
 */
fun Context.arePermissionsAlreadyAccepted(requiredPermissions: Iterable<String>): Boolean =
    requiredPermissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

/**
 *
 */
fun Context.arePermissionsAlreadyAccepted(requiredPermissions: Array<String>): Boolean {
    val iterable = ArrayList<String>()
    for (permission in requiredPermissions)
        iterable.add(permission)
    return arePermissionsAlreadyAccepted(iterable)
}