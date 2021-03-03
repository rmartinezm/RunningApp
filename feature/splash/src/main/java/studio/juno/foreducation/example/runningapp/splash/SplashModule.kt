package studio.juno.foreducation.example.runningapp.splash

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.splash.SplashFeatureFlow

/* */
val splashModule: Module = module {

    /* FEATURE FLOW */
    single<SplashFeatureFlow> { SplashFeatureFlowImpl() }

}
