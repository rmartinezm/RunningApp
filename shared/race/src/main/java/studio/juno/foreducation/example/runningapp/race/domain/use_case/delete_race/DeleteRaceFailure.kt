package studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race

import studio.juno.foreducation.example.runningapp.clean.domain.Failure
import studio.juno.foreducation.example.runningapp.failurehandler.common_failure.IOFailure

/* */
sealed class DeleteRaceFailure : Failure() {

    /**
     *
     */
    data class IOFailureImpl(
        override val message: String
    ) : DeleteRaceFailure(), IOFailure

}