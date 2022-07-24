package com.r2d.domain.builder

import com.r2d.domain.entity.Punter
import java.util.UUID

class PunterBuilder {

    var id = UUID.randomUUID().toString()
    var name = "João"

    companion object Builder {
        fun create(builder: PunterBuilder.() -> Unit): Punter {
            return PunterBuilder().apply(builder).let {
                Punter(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }


}