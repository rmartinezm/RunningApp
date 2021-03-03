package studio.juno.foreducation.example.runningapp.extension

/**
 *
 */
fun <T> List<T>.sizeIsAtLeast(n: Int): Boolean = size >= n

/**
 *
 */
fun <T> List<T>.haveAtLeastOneElement(): Boolean = size > 0