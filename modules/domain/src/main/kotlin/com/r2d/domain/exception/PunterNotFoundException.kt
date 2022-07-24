package com.r2d.domain.exception

class PunterNotFoundException(punterId: String):
    Throwable("The punter with id $punterId not found")
