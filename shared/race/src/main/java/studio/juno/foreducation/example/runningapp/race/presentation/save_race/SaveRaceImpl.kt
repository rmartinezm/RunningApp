package studio.juno.foreducation.example.runningapp.race.presentation.save_race

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.domain.onLeft
import studio.juno.foreducation.example.runningapp.clean.domain.onRight
import studio.juno.foreducation.example.runningapp.clean.presentation.Status
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceParams
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceResponse
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceUseCase

/* */
internal class SaveRaceImpl(
    private val saveRaceUseCase: SaveRaceUseCase
) : SaveRace {

    /* */
    override lateinit var saveRaceResponse: SaveRaceResponse

    /* */
    override lateinit var saveRaceFailure: SaveRaceFailure

    /**
     *
     */
    override fun saveRaceAsLiveData(params: SaveRaceParams): LiveData<SaveRaceStatus> =
        flow<SaveRaceStatus> {
            emit(Status.Loading())
            saveRaceAsEither(params)
                .onLeft { emit(Status.Failed(it)) }
                .onRight { emit(Status.Done(it)) }
        }.asLiveData(Dispatchers.IO)

    /**
     *
     */
    override suspend fun saveRaceAsEither(
        params: SaveRaceParams
    ): Either<SaveRaceFailure, SaveRaceResponse> =
        saveRaceUseCase.run(params)
            .onLeft { saveRaceFailure = it }
            .onRight { saveRaceResponse = it }

}