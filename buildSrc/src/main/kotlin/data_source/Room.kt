/* */
object Room {

    /* */
    private object Version {

        /* */
        const val room: String = "2.2.5"

    }

    /* */
    const val runtime = "androidx.room:room-runtime:${Version.room}"

    /* */
    const val compiler = "androidx.room:room-compiler:${Version.room}"

    /* */
    const val ktx = "androidx.room:room-ktx:${Version.room}"

}