package studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races

import studio.juno.foreducation.example.runningapp.clean.domain.Failure
import studio.juno.foreducation.example.runningapp.failurehandler.common_failure.IOFailure

/* */
sealed class GetAllRacesFailure : Failure() {

    /**
     *
     */
    data class IOFailureImpl(
        override val message: String
    ) : GetAllRacesFailure(), IOFailure

}