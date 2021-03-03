package studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race

import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.domain.UseCase
import studio.juno.foreducation.example.runningapp.race.domain.RaceRepository

/* */
class SaveRaceUseCase(
    private val raceRepository: RaceRepository
) : UseCase<SaveRaceResponse, SaveRaceParams, SaveRaceFailure>(){

    /**
     *
     */
    override suspend fun run(
        params: SaveRaceParams
    ): Either<SaveRaceFailure, SaveRaceResponse> =
        raceRepository.saveRace(params.race)

}