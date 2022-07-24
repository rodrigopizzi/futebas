package com.r2d.domain.usecase

import com.r2d.domain.entity.SoccerGameBet
import com.r2d.domain.exception.PunterNotFoundException
import com.r2d.domain.exception.SoccerGameBetInvalidException
import com.r2d.domain.exception.SoccerGameNotFoundException
import com.r2d.domain.port.PunterDatabasePort
import com.r2d.domain.port.SoccerGameBetDatabasePort
import com.r2d.domain.port.SoccerGameDatabasePort

class MakeSoccerGameBetUseCase(
    private val soccerGameDatabasePort: SoccerGameDatabasePort,
    private val punterDatabasePort: PunterDatabasePort,
    private val soccerGameBetDatabasePort: SoccerGameBetDatabasePort
) {

    fun execute(soccerGameBet: SoccerGameBet): SoccerGameBet {
        val soccerGame =
            soccerGameDatabasePort.findById(soccerGameBet.soccerGameId) ?: throw SoccerGameNotFoundException(
                soccerGameBet.soccerGameId
            )

        val punter =
            punterDatabasePort.findById(soccerGameBet.punterId) ?: throw PunterNotFoundException(soccerGameBet.punterId)

        if (soccerGameBet.bettingScore.team1Score < 0)
            throw SoccerGameBetInvalidException("The score for ${soccerGame.team1} should be greater or equals zero")

        if (soccerGameBet.bettingScore.team2score < 0)
            throw SoccerGameBetInvalidException("The score for ${soccerGame.team2} should be greater or equals zero")

        return when (val soccerGameBetInDatabase = soccerGameBetDatabasePort.findByPunterAndSoccerGame(punter, soccerGame)) {
            null -> soccerGameBetDatabasePort.save(soccerGameBet)
            else -> soccerGameBetInDatabase.copy(bettingScore = soccerGameBet.bettingScore).also {
                soccerGameBetDatabasePort.update(it)
            }
        }
    }
}
