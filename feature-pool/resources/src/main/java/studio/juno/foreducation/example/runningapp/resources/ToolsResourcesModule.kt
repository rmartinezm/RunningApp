package studio.juno.foreducation.example.runningapp.resources

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.tools.ToolsResourcesFeatureFlow

/* */
val toolsResourcesModule: Module = module {

    /* */
    single<ToolsResourcesFeatureFlow> { ToolsResourcesFeatureFlowImpl() }

}