package studio.juno.foreducation.example.runningapp.permissions

import android.content.pm.PackageManager

/**
 *
 */
fun toMapPermissionsResult(
    permissions: Array<out String>,
    grantResults: IntArray
): Map<String, Int> {
    val map = HashMap<String, Int>()
    permissions.forEachIndexed { i, permission ->
        map[permission] = grantResults[i]
    }
    return map
}

/**
 *
 */
fun Map<String, Int>.firstPermissionDenied(): String =
    firstPermissionDeniedOrNull()
        ?: error("The map haven't permission denied")

/**
 *
 */
fun Map<String, Int>.firstPermissionDeniedOrNull(): String? {
    forEach { entry ->
        if (entry.value == PackageManager.PERMISSION_DENIED)
            return entry.key
    }
    return null
}