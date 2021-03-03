package studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.type_converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

/* */
class BitmapTypeConverter {

    /**
     *
     */
    @TypeConverter
    fun toBitmap(data: ByteArray): Bitmap =
        BitmapFactory.decodeByteArray(data, 0, data.size)

    /**
     *
     */
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

}