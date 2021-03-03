package studio.juno.foreducation.example.runningapp.race.presentation.get_all_races

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.domain.onLeft
import studio.juno.foreducation.example.runningapp.clean.domain.onRight
import studio.juno.foreducation.example.runningapp.clean.presentation.Status
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesParams
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesUseCase

/* */
internal class GetAllRacesImpl(
    private val getAllRacesUseCase: GetAllRacesUseCase
) : GetAllRaces {

    /* */
    override lateinit var getAllRacesResponse: GetAllRacesResponse

    /* */
    override lateinit var getAllRacesFailure: GetAllRacesFailure

    /**
     *
     */
    override fun getAllRacesAsLiveData(params: GetAllRacesParams): LiveData<GetAllRacesStatus> =
        flow<GetAllRacesStatus> {
            emit(Status.Loading())
            getAllRacesAsEither(params)
                .onLeft { emit(Status.Failed(it)) }
                .onRight { emit(Status.Done(it)) }
        }.asLiveData(Dispatchers.IO)

    /**
     *
     */
    override suspend fun getAllRacesAsEither(
        params: GetAllRacesParams
    ): Either<GetAllRacesFailure, GetAllRacesResponse> =
        getAllRacesUseCase.run(params)
            .onLeft { getAllRacesFailure = it }
            .onRight { getAllRacesResponse = it }

}
