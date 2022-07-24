package com.r2d.domain.entity

import java.time.OffsetDateTime

data class SoccerGame (val id: String, val team1: String, val team2: String, val date: OffsetDateTime)