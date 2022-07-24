package com.r2d.domain.usecase

import com.r2d.domain.builder.SoccerGameBuilder
import com.r2d.domain.entity.SoccerGame
import com.r2d.domain.exception.SoccerGameAlreadyExistsException
import com.r2d.domain.port.SoccerGameDatabasePort
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

internal class CreateSoccerGameUseCaseTest {

    @MockK
    lateinit var soccerGameDatabasePort: SoccerGameDatabasePort

    @InjectMockKs
    lateinit var createSoccerGameUseCase: CreateSoccerGameUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when soccer game already exists expected exception`() {
        val soccerGame = SoccerGameBuilder.create {  }
        every { soccerGameDatabasePort.findByDate(any()) } returns listOf(soccerGame)

        assertThrows<SoccerGameAlreadyExistsException> {
            createSoccerGameUseCase.execute(soccerGame)
        }
    }

    @Test
    fun `when exists others soccer games in same day then save new game`() {
        every { soccerGameDatabasePort.findByDate(any()) } returns listOf(
            SoccerGameBuilder.create { team1 = "São Paulo" },
            SoccerGameBuilder.create { team1 = "Bota Fogo" },
            SoccerGameBuilder.create { team1 = "Cruzeiro" }
        )
        val soccerGame = SoccerGameBuilder.create { team1 = "Corinthians" }
        every { soccerGameDatabasePort.save(any()) } answers {call -> call.invocation.args[0] as SoccerGame }

        val result = createSoccerGameUseCase.execute(soccerGame)
        assertEquals(soccerGame, result)
        verify { soccerGameDatabasePort.save(any()) }
    }

    @Test
    fun `when not exists soccer games then save new game`() {
        every { soccerGameDatabasePort.findByDate(any()) } returns emptyList()
        val soccerGame = SoccerGameBuilder.create { team1 = "Corinthians" }
        every { soccerGameDatabasePort.save(any()) } answers {call -> call.invocation.args[0] as SoccerGame }

        val result = createSoccerGameUseCase.execute(soccerGame)
        assertEquals(soccerGame, result)
        verify { soccerGameDatabasePort.save(any()) }
    }
}