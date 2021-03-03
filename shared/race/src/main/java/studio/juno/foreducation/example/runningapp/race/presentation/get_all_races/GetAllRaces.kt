package studio.juno.foreducation.example.runningapp.race.presentation.get_all_races

import androidx.lifecycle.LiveData
import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.presentation.Status
import studio.juno.foreducation.example.runningapp.race.domain.entity.Race
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesParams
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesResponse

/* */
typealias GetAllRacesStatus = Status<GetAllRacesFailure, GetAllRacesResponse>

/* */
interface GetAllRaces {

    /* */
    val getAllRacesResponse: GetAllRacesResponse

    /* */
    val getAllRacesFailure: GetAllRacesFailure

    /**
     *
     */
    fun getAllRacesAsLiveData(params: GetAllRacesParams): LiveData<GetAllRacesStatus>

    /**
     *
     */
    suspend fun getAllRacesAsEither(
        params: GetAllRacesParams
    ): Either<GetAllRacesFailure, GetAllRacesResponse>

}

/* */
val GetAllRaces.races: List<Race>
    get() = getAllRacesResponse.races
