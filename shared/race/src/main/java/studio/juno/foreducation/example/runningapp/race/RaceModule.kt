package studio.juno.foreducation.example.runningapp.race

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import studio.juno.foreducation.example.runningapp.race.data.RaceDataSource
import studio.juno.foreducation.example.runningapp.race.data.RaceRepositoryImpl
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.RaceDataSourceLocal
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.RaceDatabase
import studio.juno.foreducation.example.runningapp.race.data.data_source.local.model.dao.RaceDao
import studio.juno.foreducation.example.runningapp.race.domain.RaceRepository
import studio.juno.foreducation.example.runningapp.race.domain.use_case.delete_race.DeleteRaceUseCase
import studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races.GetAllRacesUseCase
import studio.juno.foreducation.example.runningapp.race.domain.use_case.save_race.SaveRaceUseCase
import studio.juno.foreducation.example.runningapp.race.presentation.delete_race.DeleteRace
import studio.juno.foreducation.example.runningapp.race.presentation.delete_race.DeleteRaceImpl
import studio.juno.foreducation.example.runningapp.race.presentation.get_all_races.GetAllRaces
import studio.juno.foreducation.example.runningapp.race.presentation.get_all_races.GetAllRacesImpl
import studio.juno.foreducation.example.runningapp.race.presentation.save_race.SaveRace
import studio.juno.foreducation.example.runningapp.race.presentation.save_race.SaveRaceImpl

/* */
val raceModule: Module = module {

    /** PRESENTATION **/
    single<SaveRace> { SaveRaceImpl(saveRaceUseCase = get()) }
    single<DeleteRace> { DeleteRaceImpl(deleteRaceUseCase = get()) }
    single<GetAllRaces> { GetAllRacesImpl(getAllRacesUseCase = get()) }

    /** USE CASE **/
    factory { SaveRaceUseCase(raceRepository = get()) }
    factory { DeleteRaceUseCase(raceRepository = get()) }
    factory { GetAllRacesUseCase(raceRepository = get()) }

    /** REPOSITORY **/
    single<RaceRepository> {
        RaceRepositoryImpl(
            raceDataSource = get()
        )
    }

    /** DATA SOURCE **/
    single<RaceDataSource> {
        RaceDataSourceLocal(
            raceDao = get()
        )
    }

    /** DAO **/
    single<RaceDao> {
        get<RaceDatabase>().getRaceDao()
    }

    /** DATA BASE **/
    single<RaceDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            RaceDatabase::class.java,
            RaceDatabase.RACE_DATABASE_NAME
        ).build()
    }


}