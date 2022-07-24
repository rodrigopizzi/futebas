package com.r2d.domain.port

import com.r2d.domain.entity.Punter
import com.r2d.domain.entity.SoccerGame
import com.r2d.domain.entity.SoccerGameBet

interface SoccerGameBetDatabasePort {
    fun findByPunterAndSoccerGame(punter: Punter, soccerGame: SoccerGame): SoccerGameBet?
    fun update(soccerGameBet: SoccerGameBet): SoccerGameBet?
    fun save(soccerGameBet: SoccerGameBet): SoccerGameBet
}