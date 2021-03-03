package studio.juno.foreducation.example.runningapp.race_tracking

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.race_tracking.RaceTrackingFeatureFlow
import studio.juno.foreducation.example.runningapp.race_tracking.tracking_location.TrackingLocationIntentInjectorImpl
import studio.juno.foreducation.example.runningapp.tracking_location.tracking_location.service.TrackingLocationIntentInjector

/* */
val raceTrackingModule: Module = module {

    /* */
    single<RaceTrackingFeatureFlow> { RaceTrackingFeatureFlowImpl() }

    /* */
    single<TrackingLocationIntentInjector> { TrackingLocationIntentInjectorImpl() }

}