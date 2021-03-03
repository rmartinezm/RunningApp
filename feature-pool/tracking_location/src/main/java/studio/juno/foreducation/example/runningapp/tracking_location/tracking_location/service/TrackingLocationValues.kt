package studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.model.Polylines
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.TrackingLocationAction

/* */
object TrackingLocationValues {

    /* */
    internal val _actionTakenLiveData: MutableLiveData<TrackingLocationAction> =
        MutableLiveData()
    val actionTakenLiveData: LiveData<TrackingLocationAction> get() = _actionTakenLiveData

    /* */
    internal val _polylinesLiveData: MutableLiveData<Polylines> = MutableLiveData()
    val polylinesLiveData: LiveData<Polylines> get() = _polylinesLiveData

    /* */
    internal val _timeRunningInSecondsLiveData: MutableLiveData<Long> = MutableLiveData()
    val timeRunningInSecondsLiveData: LiveData<Long> get() = _timeRunningInSecondsLiveData

    /**
     *
     */
    internal fun setupInitialValues() {
        _actionTakenLiveData.postValue(TrackingLocationAction.INITIALIZE)
        _polylinesLiveData.postValue(mutableListOf())
        _timeRunningInSecondsLiveData.postValue(0L)
    }

}