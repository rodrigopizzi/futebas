package com.r2d.domain.port

import com.r2d.domain.entity.Punter

interface PunterDatabasePort {
    fun findById(id: String): Punter?
}