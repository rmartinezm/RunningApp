package studio.juno.foreducation.example.runningapp.race.data.data_source.local

import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.race.data.RaceDataSource
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.dao.RaceDao
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.entity.RaceDto
import studio.juno.foreducation.example.runningapp.race.domain.entity.Race
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceResponse

/* */
internal class RaceDataSourceLocal(
    private val raceDao: RaceDao
): RaceDataSource {

    /**
     *
     */
    override suspend fun saveRace(race: Race): Either<SaveRaceFailure, SaveRaceResponse> {
        val raceDto = RaceDto.fromRace(race)
        raceDao.insertRace(raceDto)
        val response = SaveRaceResponse
        return Either.Right(response)
    }

    /**
     *
     */
    override suspend fun deleteRace(raceId: Int): Either<DeleteRaceFailure, DeleteRaceResponse> {
        raceDao.deleteRaceFromId(raceId)
        val response = DeleteRaceResponse
        return Either.Right(response)
    }

    /**
     *
     */
    override suspend fun getAllRaces(): Either<GetAllRacesFailure, GetAllRacesResponse> {
        val racesDto = raceDao.getAllRaces()
        val races = racesDto.map { it.toRace() }
        val response = GetAllRacesResponse(races)
        return Either.Right(response)
    }

}