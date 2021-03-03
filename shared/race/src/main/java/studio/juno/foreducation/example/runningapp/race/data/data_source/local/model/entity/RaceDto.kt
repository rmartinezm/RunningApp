package studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import studio.juno.foreducation.example.runningapp.race.domain.entity.Race

/* */
@Entity(tableName = "running_table")
data class RaceDto(
    val timestamp: Long,
    val bitmapImage: Bitmap,
    val distanceInMeters: Int,
    val timeInMillis: Long,
    val avgSpeedInKmPerH: Int,
    val caloriesBurned: Int,
) {

    /* */
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    /**
     *
     */
    fun toRace(): Race =
        Race(
            id!!,
            timestamp,
            bitmapImage,
            distanceInMeters,
            timeInMillis,
            avgSpeedInKmPerH,
            caloriesBurned
        )

    /* */
    companion object {

        /**
         *
         */
        fun fromRace(it: Race): RaceDto =
            RaceDto(
                it.timestamp,
                it.bitmapImage,
                it.distanceInMeters,
                it.timeInMillis,
                it.avgSpeedInKmPerH,
                it.caloriesBurned
            ).apply {
                if (it.id > 0)
                    id = it.id
            }

    }

}
