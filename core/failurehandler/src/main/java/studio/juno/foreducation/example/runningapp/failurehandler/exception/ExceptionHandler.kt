package studio.juno.foreducation.example.runningapp.failurehandler.exception

/**
 *
 */
fun <T> tryOrNull(throwableAction: () -> T): T? =
    try {
        throwableAction()
    } catch (ignore: Exception) {
        null
    }