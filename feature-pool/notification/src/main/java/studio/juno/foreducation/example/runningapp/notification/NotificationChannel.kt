package studio.juno.foreducation.example.runningapp.notification

import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

/* */
@RequiresApi(Build.VERSION_CODES.O)
fun Context.createNotificationChannel(
    channelId: String,
    channelName: String,
    importance: Int
) {
    val notificationManager = getNotificationManager()
    val channel = NotificationChannel(channelId, channelName, importance)
    notificationManager.createNotificationChannel(channel)
}
