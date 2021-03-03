package studio.juno.foreducation.example.runningapp.race.domain.use_case.get_all_races

import studio.juno.foreducation.example.runningapp.race.domain.entity.Race

/* */
data class GetAllRacesResponse(
    val races: List<Race>
)