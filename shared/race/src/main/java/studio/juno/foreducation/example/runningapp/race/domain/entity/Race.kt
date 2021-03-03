package studio.juno.foreducation.example.runningapp.race.domain.entity

import android.graphics.Bitmap

/* */
data class Race(
    val id: Int = 0,
    val timestamp: Long,
    val bitmapImage: Bitmap,
    val distanceInMeters: Int,
    val timeInMillis: Long,
    val avgSpeedInKmPerH: Int,
    val caloriesBurned: Int,
)