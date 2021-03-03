package studio.juno.foreducation.example.runningapp.navigation.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment

/* */
const val APPLICATION_DETAILS_SETTINGS_REQUEST_CODE: Int = 100

/**
 *
 */
fun Activity.navigateToApplicationDetailsSettings() {
    val intent = getIntentApplicationDetailsSettings()
    startActivityForResult(intent, APPLICATION_DETAILS_SETTINGS_REQUEST_CODE)
}

/**
 *
 */
fun Fragment.navigateToApplicationDetailsSettings() {
    val intent = requireContext().getIntentApplicationDetailsSettings()
    startActivityForResult(intent, APPLICATION_DETAILS_SETTINGS_REQUEST_CODE)
}

/* */
private fun Context.getIntentApplicationDetailsSettings(): Intent =
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    )
