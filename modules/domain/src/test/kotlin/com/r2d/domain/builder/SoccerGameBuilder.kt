package com.r2d.domain.builder

import com.r2d.domain.entity.SoccerGame
import java.time.OffsetDateTime
import java.util.UUID

class SoccerGameBuilder {

    var id = UUID.randomUUID().toString()
    var team1 = "Flamengo"
    var team2 = "Fluminense"
    var date: OffsetDateTime = OffsetDateTime.now()

    companion object Builder {
        fun create(builder: SoccerGameBuilder.() -> Unit): SoccerGame {
            return SoccerGameBuilder().apply(builder).let {
                SoccerGame(id = it.id, team1 = it.team1, team2 = it.team2, date = it.date)
            }
        }
    }


}