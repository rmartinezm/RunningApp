package studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/* */
internal object TrackingLocationPolylineManager {

    /**
     *
     */
    fun addEmptyPolyline() {
        val polylines = TrackingLocationValues.polylinesLiveData.value?.apply {
            add(mutableListOf())
        } ?: mutableListOf(mutableListOf())
        TrackingLocationValues._polylinesLiveData.postValue(polylines)
    }

    /**
     *
     */
    fun addPathPoint(location: Location?) {
        location?.let {
            val latLng = LatLng(it.latitude, it.longitude)
            TrackingLocationValues.polylinesLiveData.value?.apply {
                last().add(latLng)
                TrackingLocationValues._polylinesLiveData.postValue(this)
            }
        }
    }

}