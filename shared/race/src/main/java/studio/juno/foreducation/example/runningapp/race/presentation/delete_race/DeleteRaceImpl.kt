package studio.juno.foreducation.example.runningapp.race.presentation.delete_race

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.domain.onLeft
import studio.juno.foreducation.example.runningapp.clean.domain.onRight
import studio.juno.foreducation.example.runningapp.clean.presentation.Status
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceParams
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceUseCase

/* */
internal class DeleteRaceImpl(
    private val deleteRaceUseCase: DeleteRaceUseCase
) : DeleteRace {

    /* */
    override lateinit var deleteRaceResponse: DeleteRaceResponse

    /* */
    override lateinit var deleteRaceFailure: DeleteRaceFailure

    /**
     *
     */
    override fun deleteRaceAsLiveData(params: DeleteRaceParams): LiveData<DeleteRaceStatus> =
        flow<DeleteRaceStatus> {
            emit(Status.Loading())
            deleteRaceAsEither(params)
                .onLeft { emit(Status.Failed(it)) }
                .onRight { emit(Status.Done(it)) }
        }.asLiveData(Dispatchers.IO)

    /**
     *
     */
    override suspend fun deleteRaceAsEither(
        params: DeleteRaceParams
    ): Either<DeleteRaceFailure, DeleteRaceResponse> =
        deleteRaceUseCase.run(params)
            .onLeft { deleteRaceFailure = it }
            .onRight { deleteRaceResponse = it }

}
