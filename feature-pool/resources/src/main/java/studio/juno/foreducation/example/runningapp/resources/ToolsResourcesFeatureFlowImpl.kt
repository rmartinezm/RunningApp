package studio.juno.foreducation.example.runningapp.resources

import android.content.Context
import studio.juno.foreducation.example.runningapp.navigation.extension.navigateTo
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.tools.ToolsResourcesFeatureFlow
import studio.juno.foreducation.example.runningapp.resources.presentation.ToolsResourcesActivity

/* */
internal class ToolsResourcesFeatureFlowImpl : ToolsResourcesFeatureFlow {

    /**
     *
     */
    override fun execute(context: Context) {
        context.navigateTo(ToolsResourcesActivity::class.java, clearTop = true)
    }

}