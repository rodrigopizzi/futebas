package com.r2d.domain.usecase

import com.r2d.domain.entity.SoccerGame
import com.r2d.domain.exception.SoccerGameAlreadyExistsException
import com.r2d.domain.port.SoccerGameDatabasePort

class CreateSoccerGameUseCase(private val soccerGameDatabasePort: SoccerGameDatabasePort) {

    fun execute(soccerGame: SoccerGame): SoccerGame {
        val games = soccerGameDatabasePort.findByDate(soccerGame.date)
        games.find { it.team1 == soccerGame.team1 && it.team2 == soccerGame.team2 }?.let {
            throw SoccerGameAlreadyExistsException(soccerGame)
        }
        return soccerGameDatabasePort.save(soccerGame)
    }

}