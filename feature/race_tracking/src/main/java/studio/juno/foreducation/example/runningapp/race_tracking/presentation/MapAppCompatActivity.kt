package studio.juno.foreducation.example.runningapp.race_tracking.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView

/* */
abstract class MapAppCompatActivity : AppCompatActivity() {

    /* */
    protected var googleMap: GoogleMap? = null

    /* */
    protected var mapView: MapView? = null

    /**
     *
     */
    protected fun setupMapView(
        mapView: MapView,
        savedInstanceState: Bundle?,
        onMapAssociated: (() -> Unit)? = null
    ) {
        this.mapView = mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            googleMap = it
            onMapAssociated?.invoke()
        }
    }

    /**
     *
     */
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    /**
     *
     */
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    /**
     *
     */
    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    /**
     *
     */
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    /**
     *
     */
    override fun onDestroy() {
        mapView?.onDestroy()
        super.onDestroy()
    }

    /**
     *
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

}