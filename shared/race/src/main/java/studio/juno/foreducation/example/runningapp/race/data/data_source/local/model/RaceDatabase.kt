package studio.juno.foreducation.example.runningapp.race.data.data_source.local.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.dao.RaceDao
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.entity.RaceDto
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.type_converter.BitmapTypeConverter

@Database(
    entities = [RaceDto::class],
    version = 1
)
@TypeConverters(BitmapTypeConverter::class)
internal abstract class RaceDatabase : RoomDatabase() {

    /**
     *
     */
    abstract fun getRaceDao(): RaceDao

    /* */
    companion object {

        /* */
        const val RACE_DATABASE_NAME: String = "running_db"

    }

}