package com.r2d.domain.exception

import com.r2d.domain.entity.SoccerGame

class SoccerGameAlreadyExistsException(soccerGame: SoccerGame) :
    Throwable("Already exists soccer game between ${soccerGame.team1} and ${soccerGame.team2} in ${soccerGame.date}")
