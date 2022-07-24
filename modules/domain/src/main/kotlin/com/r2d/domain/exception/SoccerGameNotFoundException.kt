package com.r2d.domain.exception

class SoccerGameNotFoundException(soccerGameId: String):
    Throwable("The soccer game with id $soccerGameId not found")