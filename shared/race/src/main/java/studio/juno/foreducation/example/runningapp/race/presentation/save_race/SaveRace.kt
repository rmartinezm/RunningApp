package studio.juno.foreducation.example.runningapp.race.presentation.save_race

import androidx.lifecycle.LiveData
import studio.juno.foreducation.example.runningapp.clean.domain.Either
import studio.juno.foreducation.example.runningapp.clean.presentation.Status
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceFailure
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceParams
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceResponse

/* */
typealias SaveRaceStatus = Status<SaveRaceFailure, SaveRaceResponse>

/* */
interface SaveRace {

    /* */
    val saveRaceResponse: SaveRaceResponse

    /* */
    val saveRaceFailure: SaveRaceFailure

    /**
     *
     */
    fun saveRaceAsLiveData(params: SaveRaceParams): LiveData<SaveRaceStatus>

    /**
     *
     */
    suspend fun saveRaceAsEither(
        params: SaveRaceParams
    ): Either<SaveRaceFailure, SaveRaceResponse>

}