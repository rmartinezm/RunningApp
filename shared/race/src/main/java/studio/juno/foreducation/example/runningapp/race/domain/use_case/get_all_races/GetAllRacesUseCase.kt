package studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races

import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.domain.UseCase
import studio.juno.foreducation.example.runningapp.race.domain.RaceRepository

/* */
class GetAllRacesUseCase(
    private val raceRepository: RaceRepository
) : UseCase<GetAllRacesResponse, GetAllRacesParams, GetAllRacesFailure>() {

    /**
     *
     */
    override suspend fun run(
        params: GetAllRacesParams
    ): Either<GetAllRacesFailure, GetAllRacesResponse> =
        raceRepository.getAllRaces()

}