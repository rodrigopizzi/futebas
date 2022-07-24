package com.r2d.domain.port

import com.r2d.domain.entity.SoccerGame
import java.time.OffsetDateTime

interface SoccerGameDatabasePort {
    fun findByDate(date: OffsetDateTime): List<SoccerGame>
    fun save(soccerGame: SoccerGame): SoccerGame
    fun findById(id: String): SoccerGame?
}
