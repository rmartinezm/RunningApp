package studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race

import studio.juno.foreducation.example.runningapp.clean.domain.Failure
import studio.juno.foreducation.example.runningapp.failurehandler.common_failure.IOFailure

/* */
sealed class SaveRaceFailure : Failure() {

    /**
     *
     */
    data class IOFailureImpl(
        override val message: String
    ) : SaveRaceFailure(), IOFailure

}