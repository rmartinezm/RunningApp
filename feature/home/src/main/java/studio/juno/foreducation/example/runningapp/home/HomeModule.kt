package studio.juno.foreducation.example.runningapp.home

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.home.HomeFeatureFlow

/* */
val homeModule: Module = module {

    /* */
    single<HomeFeatureFlow> { HomeFeatureFlowImpl()  }

}