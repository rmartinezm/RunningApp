package studio.juno.foreducation.example.runningapp.navigation.extension

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * 
 */
fun <T> Fragment.navigateTo(javaClass: Class<T>, clearTop: Boolean = false) {
    requireContext().navigateTo(javaClass, clearTop)
}

/**
 *
 */
fun <T> Context.navigateTo(javaClass: Class<T>, clearTop: Boolean = false) {
    Intent(this, javaClass).apply {
        if (clearTop)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(this)
    }
}