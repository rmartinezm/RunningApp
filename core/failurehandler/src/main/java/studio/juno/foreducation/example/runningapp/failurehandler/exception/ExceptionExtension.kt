package studio.juno.foreducation.example.runningapp.failurehandler.exception

/**
 *
 */
fun Exception.getMessageOrName(): String = message ?: javaClass.simpleName