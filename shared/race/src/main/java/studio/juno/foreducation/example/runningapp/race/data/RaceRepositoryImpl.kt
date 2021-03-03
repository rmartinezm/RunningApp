package studio.juno.foreducation.example.runningapp.race.data

import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.failurehandler.exception.getMessageOrName
import studio.juno.foreducation.example.runningapp.race.domain.RaceRepository
import studio.juno.foreducation.example.runningapp.race.domain.entity.Race
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceResponse

/* */
internal class RaceRepositoryImpl(
    private val raceDataSource: RaceDataSource
) : RaceRepository {

    /**
     *
     */
    override suspend fun saveRace(race: Race): Either<SaveRaceFailure, SaveRaceResponse> =
        try {
            raceDataSource.saveRace(race)
        } catch (exception: Exception) {
            Either.Left(SaveRaceFailure.IOFailureImpl(exception.getMessageOrName()))
        }

    /**
     *
     */
    override suspend fun deleteRace(raceId: Int): Either<DeleteRaceFailure, DeleteRaceResponse> =
        try {
            raceDataSource.deleteRace(raceId)
        } catch (exception: Exception) {
            Either.Left(DeleteRaceFailure.IOFailureImpl(exception.getMessageOrName()))
        }

    /**
     *
     */
    override suspend fun getAllRaces(): Either<GetAllRacesFailure, GetAllRacesResponse> =
        try {
            raceDataSource.getAllRaces()
        } catch (exception: Exception) {
            Either.Left(GetAllRacesFailure.IOFailureImpl(exception.getMessageOrName()))
        }

}