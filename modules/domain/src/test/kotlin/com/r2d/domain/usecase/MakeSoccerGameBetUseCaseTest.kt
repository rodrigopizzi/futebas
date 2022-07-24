package com.r2d.domain.usecase

import com.r2d.domain.builder.PunterBuilder
import com.r2d.domain.builder.SoccerGameBetBuilder
import com.r2d.domain.builder.SoccerGameBuilder
import com.r2d.domain.entity.BettingScore
import com.r2d.domain.entity.SoccerGameBet
import com.r2d.domain.exception.PunterNotFoundException
import com.r2d.domain.exception.SoccerGameBetInvalidException
import com.r2d.domain.exception.SoccerGameNotFoundException
import com.r2d.domain.port.PunterDatabasePort
import com.r2d.domain.port.SoccerGameBetDatabasePort
import com.r2d.domain.port.SoccerGameDatabasePort
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class MakeSoccerGameBetUseCaseTest {

    @MockK
    lateinit var soccerGameDatabasePort: SoccerGameDatabasePort

    @MockK
    lateinit var punterDatabasePort: PunterDatabasePort

    @MockK
    lateinit var soccerGameBetDatabasePort: SoccerGameBetDatabasePort

    @InjectMockKs
    lateinit var makeSoccerGameBetUseCase: MakeSoccerGameBetUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when soccer game not exists`() {
        every { soccerGameDatabasePort.findById(any()) } returns null

        assertThrows<SoccerGameNotFoundException> {
            makeSoccerGameBetUseCase.execute(SoccerGameBetBuilder.create { })
        }
    }

    @Test
    fun `when punter not exists`() {
        every { soccerGameDatabasePort.findById(any()) } returns SoccerGameBuilder.create { }
        every { punterDatabasePort.findById(any()) } returns null

        assertThrows<PunterNotFoundException> {
            makeSoccerGameBetUseCase.execute(SoccerGameBetBuilder.create { })
        }
    }

    @Test
    fun `when betting score has invalid value for team 1`() {
        every { soccerGameDatabasePort.findById(any()) } returns SoccerGameBuilder.create { }
        every { punterDatabasePort.findById(any()) } returns PunterBuilder.create { }

        assertThrows<SoccerGameBetInvalidException> {
            makeSoccerGameBetUseCase.execute(SoccerGameBetBuilder.create {
                bettingScore = BettingScore(team1Score = -1, team2score = 0)
            })
        }
    }

    @Test
    fun `when betting score has invalid value for team 2`() {
        every { soccerGameDatabasePort.findById(any()) } returns SoccerGameBuilder.create { }
        every { punterDatabasePort.findById(any()) } returns PunterBuilder.create { }

        assertThrows<SoccerGameBetInvalidException> {
            makeSoccerGameBetUseCase.execute(SoccerGameBetBuilder.create {
                bettingScore = BettingScore(team1Score = 0, team2score = -1)
            })
        }
    }

    @Test
    fun `when bet is new then save it`() {
        val newSoccerGameBet = SoccerGameBetBuilder.create {
            bettingScore = BettingScore(
                team1Score = 7, team2score = 1
            )
        }
        every { soccerGameDatabasePort.findById(any()) } returns SoccerGameBuilder.create {
            id = newSoccerGameBet.soccerGameId
        }
        every { punterDatabasePort.findById(any()) } returns PunterBuilder.create {  }
        every { soccerGameBetDatabasePort.findByPunterAndSoccerGame(any(), any()) } returns null
        every { soccerGameBetDatabasePort.save(any()) } answers {call ->  call.invocation.args[0] as SoccerGameBet }

        val result = makeSoccerGameBetUseCase.execute(newSoccerGameBet)
        assertEquals(newSoccerGameBet, result)
        verify { soccerGameBetDatabasePort.save(any()) }
        verify(inverse = true) { soccerGameBetDatabasePort.update(any()) }
    }

    @Test
    fun `when bet already exists then update it`() {
        val soccerGameBetAlreadyExists = SoccerGameBetBuilder.create { }
        val newSoccerGameBet = soccerGameBetAlreadyExists.copy(
            bettingScore = BettingScore(
                team1Score = 3, team2score = 2
            )
        )
        every { soccerGameDatabasePort.findById(any()) } returns SoccerGameBuilder.create {
            id = soccerGameBetAlreadyExists.soccerGameId
        }
        every { punterDatabasePort.findById(any()) } returns PunterBuilder.create {  }
        every { soccerGameBetDatabasePort.findByPunterAndSoccerGame(any(), any()) } returns soccerGameBetAlreadyExists
        every { soccerGameBetDatabasePort.update(any()) } answers {call ->  call.invocation.args[0] as SoccerGameBet }

        val result = makeSoccerGameBetUseCase.execute(newSoccerGameBet)
        assertNotEquals(soccerGameBetAlreadyExists, result)
        assertEquals(newSoccerGameBet, result)
        verify { soccerGameBetDatabasePort.update(any()) }
        verify(inverse = true) { soccerGameBetDatabasePort.save(any()) }
    }

    @Test
    fun `when betting score has 0x0 then save it`() {
        val newSoccerGameBet = SoccerGameBetBuilder.create {
            bettingScore = BettingScore(
                team1Score = 0, team2score = 0
            )
        }
        every { soccerGameDatabasePort.findById(any()) } returns SoccerGameBuilder.create {
            id = newSoccerGameBet.soccerGameId
        }
        every { punterDatabasePort.findById(any()) } returns PunterBuilder.create {  }
        every { soccerGameBetDatabasePort.findByPunterAndSoccerGame(any(), any()) } returns null
        every { soccerGameBetDatabasePort.save(any()) } answers {call ->  call.invocation.args[0] as SoccerGameBet }

        val result = makeSoccerGameBetUseCase.execute(newSoccerGameBet)
        assertEquals(newSoccerGameBet, result)
        verify { soccerGameBetDatabasePort.save(any()) }
        verify(inverse = true) { soccerGameBetDatabasePort.update(any()) }
    }

}