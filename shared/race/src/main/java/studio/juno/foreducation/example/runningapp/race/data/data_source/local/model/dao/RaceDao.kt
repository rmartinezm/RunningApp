package studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.dao

import androidx.room.*
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.entity.RaceDto

@Dao
internal interface RaceDao {

    /**
     *
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRace(raceDto: RaceDto)

    /**
     *
     */
    @Query("DELETE FROM running_table WHERE id = :raceId")
    suspend fun deleteRaceFromId(raceId: Int)

    /**
     *
     */
    @Query("SELECT * FROM running_table")
    suspend fun getAllRaces(): List<RaceDto>

}