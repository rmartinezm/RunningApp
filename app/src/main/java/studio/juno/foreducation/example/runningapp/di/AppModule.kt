package studio.juno.foreducation.example.runningapp.di

import org.koin.android.ext.android.startKoin
import studio.juno.foreducation.example.runningapp.RunningAppApplication

/**
 * Initialize the Koin instance with the modules associated to the project.
 */
fun RunningAppApplication.initKoin() {
    val sharedModules = getSharedPoolModules() + getSharedModules()
    val featureModules = getFeatureModules()
    val toolModules = getToolModules()
    val modules = sharedModules + featureModules + toolModules
    startKoin(applicationContext, modules)
}
