package entity.usecases.retrieve

import entity.ticket.Airport
import entity.ticket.Company
import entity.ticket.Ticket
import entity.usecases.integration.AirlineTicketIntegration
import entity.usecases.integration.dto.AirlineTicketIntegrationOutput
import entity.usecases.integration.dto.IntegrationOutput
import entity.usecases.integration.dto.RetrieveTicketOutput
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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

    // retrieve success with an integration fail

    // retrieve success with two valid integrations, tickets with same price (100.0,100.0)

    // retrieve error when all integrations fail
}
