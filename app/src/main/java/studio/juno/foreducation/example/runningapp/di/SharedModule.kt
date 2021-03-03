package studio.juno.foreducation.example.runningapp.di

import org.koin.dsl.module.Module
import studio.juno.foreducation.example.runningapp.race.raceModule

/**
 *
 */
fun getSharedPoolModules(): List<Module> = listOf(

)

/**
 *
 */
fun getSharedModules(): List<Module> = listOf(
    raceModule,
)