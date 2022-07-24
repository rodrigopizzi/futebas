package com.r2d.domain.builder

import com.r2d.domain.entity.BettingScore
import com.r2d.domain.entity.SoccerGameBet
import java.util.UUID

class SoccerGameBetBuilder {

    var id = UUID.randomUUID().toString()
    var punterId = UUID.randomUUID().toString()
    var soccerGameId = UUID.randomUUID().toString()
    var bettingScore = BettingScore(team1Score = 0, team2score = 0)

    companion object Builder {
        fun create(builder: SoccerGameBetBuilder.() -> Unit): SoccerGameBet {
            return SoccerGameBetBuilder().apply(builder).let {
                SoccerGameBet(
                    id = it.id,
                    punterId = it.punterId,
                    soccerGameId = it.soccerGameId,
                    bettingScore = it.bettingScore
                )
            }
        }
    }


}