package studio.juno.foreducation.example.runningapp.race.presentation.delete_race

import androidx.lifecycle.LiveData
import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.presentation.Status
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceParams
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceResponse

/* */
typealias DeleteRaceStatus = Status<DeleteRaceFailure, DeleteRaceResponse>

/* */
interface DeleteRace {

    /* */
    val deleteRaceResponse: DeleteRaceResponse

    /* */
    val deleteRaceFailure: DeleteRaceFailure

    /**
     *
     */
    fun deleteRaceAsLiveData(params: DeleteRaceParams): LiveData<DeleteRaceStatus>

    /**
     *
     */
    suspend fun deleteRaceAsEither(
        params: DeleteRaceParams
    ): Either<DeleteRaceFailure, DeleteRaceResponse>

}