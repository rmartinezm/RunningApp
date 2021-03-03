package studio.juno.foreducation.example.runningapp.di

import org.koin.dsl.module.Module
import studio.juno.foreducation.example.runningapp.resources.toolsResourcesModule

/**
 *
 */
fun getToolModules(): List<Module> = listOf(
    toolsResourcesModule,
)
