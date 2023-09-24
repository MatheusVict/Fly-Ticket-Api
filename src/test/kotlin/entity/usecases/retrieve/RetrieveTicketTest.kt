package entity.usecases.retrieve

import com.github.ata.usecases.integration.AirlineTicketIntegration
import com.github.ata.usecases.integration.dto.IntegrationOutput
import com.github.ata.usecases.integration.dto.RetrieveTicketOutput
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.github.ata.usecases.retrieve.RetrieveTicket
import utils.ModelBuilder.createAirlineTicketInput
import utils.ModelBuilder.createAirlineTicketIntegrationOutput
import utils.ModelBuilder.createRetrieveTicketInput
import utils.ModelBuilder.createTicket

class RetrieveTicketTest {

    private val firstIntegration: AirlineTicketIntegration = mockk()
    private val secondIntegration: AirlineTicketIntegration = mockk()
    private val retrieveTicket = RetrieveTicket(
        listOf(firstIntegration, secondIntegration)
    )

    @Test
    fun `should return retrieve with success when ticket have smallest price`() {
        val integrationInput = createAirlineTicketInput()

        val retrieveTicketInput = createRetrieveTicketInput()
        val firstIntegrationResponse = IntegrationOutput.IntegrationSuccess(
            data = createAirlineTicketIntegrationOutput(
                companyName = "LATAM Airlines Brasil",
                lowestPrice = 100.0
            )
        )

        val secondIntegrationResponse = IntegrationOutput.IntegrationSuccess(
            data = createAirlineTicketIntegrationOutput(
                companyName = "GOL Airlines Brasil",
                lowestPrice = 150.0
            )
        )

        every {
            firstIntegration.integrate(integrationInput)
        } returns firstIntegrationResponse

        every {
            secondIntegration.integrate(integrationInput)
        } returns secondIntegrationResponse

        val expected = RetrieveTicketOutput.RetrieveTicketSuccess(
            ticket = createTicket(
                companyName = "LATAM Airlines Brasil",
                airportOrigin = "FLN",
                airportDestination = "GRU",
                price = 100.0
            )
        )

        val actual = retrieveTicket.retrieve(retrieveTicketInput)

        assertEquals(expected, actual)
    }

    @Test
    fun `should return retrieve success with smallest ticket price and on integration fail`() {
        val integrationInput = createAirlineTicketInput()

        val retrieveTicketInput = createRetrieveTicketInput()
        val firstIntegrationResponse = IntegrationOutput.IntegrationSuccess(
            data = createAirlineTicketIntegrationOutput(
                companyName = "LATAM Airlines Brasil",
                lowestPrice = 100.0
            )
        )

        val secondIntegrationResponse = IntegrationOutput.IntegrationFailure(
            error = "Integration error"
        )

        every {
            firstIntegration.integrate(integrationInput)
        } returns firstIntegrationResponse

        every {
            secondIntegration.integrate(integrationInput)
        } returns secondIntegrationResponse

        val expected = RetrieveTicketOutput.RetrieveTicketSuccess(
            ticket = createTicket(
                companyName = "LATAM Airlines Brasil",
                airportOrigin = "FLN",
                airportDestination = "GRU",
                price = 100.0
            )
        )

        val actual = retrieveTicket.retrieve(retrieveTicketInput)

        assertEquals(expected, actual)
    }

    @Test
    fun `should return retrieve error when all integration fail`() {
        val integrationInput = createAirlineTicketInput()

        val retrieveTicketInput = createRetrieveTicketInput()
        val firstIntegrationResponse = IntegrationOutput.IntegrationFailure(
            error = "Integration error"
        )

        val secondIntegrationResponse = IntegrationOutput.IntegrationFailure(
            error = "Integration error"
        )

        every {
            firstIntegration.integrate(integrationInput)
        } returns firstIntegrationResponse

        every {
            secondIntegration.integrate(integrationInput)
        } returns secondIntegrationResponse

        val expected = RetrieveTicketOutput.RetrieveTicketFailure(
            message = "Could not extract ticket for FLN to GRU"
        )

        val actual = retrieveTicket.retrieve(retrieveTicketInput)

        assertEquals(expected, actual)
    }

    @Test
    fun `should return retrieve with two integrations returning tickets with same lowest price`() {
        val integrationInput = createAirlineTicketInput()

        val retrieveTicketInput = createRetrieveTicketInput()
        val firstIntegrationResponse = IntegrationOutput.IntegrationSuccess(
            data = createAirlineTicketIntegrationOutput(
                companyName = "LATAM Airlines Brasil",
                lowestPrice = 100.0
            )
        )

        val secondIntegrationResponse = IntegrationOutput.IntegrationSuccess(
            data = createAirlineTicketIntegrationOutput(
                companyName = "GOL Airlines Brasil",
                lowestPrice = 100.0
            )
        )

        every {
            firstIntegration.integrate(integrationInput)
        } returns firstIntegrationResponse

        every {
            secondIntegration.integrate(integrationInput)
        } returns secondIntegrationResponse

        val expected = RetrieveTicketOutput.RetrieveTicketSuccess(
            ticket = createTicket(
                companyName = "LATAM Airlines Brasil",
                airportOrigin = "FLN",
                airportDestination = "GRU",
                price = 100.0
            )
        )

        val actual = retrieveTicket.retrieve(retrieveTicketInput)

        assertEquals(expected, actual)
    }

    // retrieve success with two valid integrations, tickets with same price (100.0,100.0)

    // retrieve error when all integrations fail
}
