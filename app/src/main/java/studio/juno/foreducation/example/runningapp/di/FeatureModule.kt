package studio.juno.foreducation.example.runningapp.di

import org.koin.dsl.module.Module
import studio.juno.foreducation.example.runningapp.race_tracking.raceTrackingModule
import studio.juno.foreducation.example.runningapp.home.homeModule
import studio.juno.foreducation.example.runningapp.splash.splashModule

/**
 *
 */
fun getFeatureModules(): List<Module> = listOf(
    splashModule,
    homeModule,
    raceTrackingModule
)
