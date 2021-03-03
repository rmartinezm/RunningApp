package studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race

import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.domain.UseCase
import studio.juno.foreducation.example.runningapp.race.domain.RaceRepository

/* */
class DeleteRaceUseCase(
    private val raceRepository: RaceRepository
) : UseCase<DeleteRaceResponse, DeleteRaceParams, DeleteRaceFailure>() {

    /**
     *
     */
    override suspend fun run(
        params: DeleteRaceParams
    ): Either<DeleteRaceFailure, DeleteRaceResponse> =
        raceRepository.deleteRace(params.raceId)
}