/* */
object GooglePlayServices {

    /* */
    private object Version {

        /* */
        const val playServicesLocation: String = "17.1.0"

        /* */
        const val playServicesMaps: String = "17.0.0"

    }

    /* */
    const val playServiceLocation = "com.google.android.gms:play-services-location:${Version.playServicesLocation}"

    /* */
    const val playServiceMaps = "com.google.android.gms:play-services-maps:${Version.playServicesMaps}"

}