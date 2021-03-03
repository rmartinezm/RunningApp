package studio.juno.foreducation.example.runningapp.notification

import android.app.NotificationManager
import android.content.Context

/**
 *
 */
fun Context.getNotificationManager(): NotificationManager =
    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager