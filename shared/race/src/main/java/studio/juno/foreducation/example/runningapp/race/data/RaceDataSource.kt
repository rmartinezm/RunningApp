package studio.juno.foreducation.example.runningapp.race.data

import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.race.domain.entity.Race
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceResponse

/* */
internal interface RaceDataSource {

    /**
     *
     */
    suspend fun saveRace(
        race: Race
    ): Either<SaveRaceFailure, SaveRaceResponse>

    /**
     *
     */
    suspend fun deleteRace(
        raceId: Int
    ): Either<DeleteRaceFailure, DeleteRaceResponse>

    /**
     *
     */
    suspend fun getAllRaces(): Either<GetAllRacesFailure, GetAllRacesResponse>

}