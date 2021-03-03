package studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import studio.juno.foreducation.example.runningapp.notification.createNotificationChannel
import studio.juno.foreducation.example.runningapp.tracking_location.R

/* */
internal data class TrackingLocationNotificationManager(
    private val context: Context
) {

    /* */
    val foregroundNotificationId: Int = 1

    /**
     *
     */
    init {
        createNotificationChannelIfIsNeeded()
    }

    /**
     *
     */
    private fun createNotificationChannelIfIsNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.createNotificationChannel(
                getChannelId(),
                context.getString(R.string.notification_channel_name_tracking_location),
                NotificationManager.IMPORTANCE_LOW
            )
        }
    }

    /**
     *
     */
    fun getNotification(contentText: String, pendingIntent: PendingIntent?): Notification =
        NotificationCompat.Builder(context, getChannelId())
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_running_notification)
            .setContentTitle(context.getString(R.string.tracking_location_notification_title))
            .setContentText(contentText)
            .addPendingIntentIfExists(pendingIntent)
            .build()

    /* */
    private fun NotificationCompat.Builder.addPendingIntentIfExists(
        pendingIntent: PendingIntent?
    ): NotificationCompat.Builder =
        this.apply {
            pendingIntent?.let {
                setContentIntent(it)
            }
        }

    /* */
    private fun getChannelId(): String =
        context.getString(R.string.notification_channel_id_tracking_location)

}