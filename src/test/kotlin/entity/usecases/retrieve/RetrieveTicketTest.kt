package entity.usecases.retrieve

import entity.ticket.Airport
import entity.ticket.Company
import entity.ticket.Ticket
import entity.usecases.integration.AirlineTicketIntegration
import entity.usecases.integration.dto.AirLaneTicketIntegrationInput
import entity.usecases.integration.dto.AirlineTicketIntegrationOutput
import entity.usecases.integration.dto.IntegrationOutput
import entity.usecases.integration.dto.RetrieveTicketOutput
import entity.usecases.retrieve.dto.RetrieveTicketInput
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RetrieveTicketTest {

    private val firstIntegration: AirlineTicketIntegration = mockk()
    private val secondIntegration: AirlineTicketIntegration = mockk()
    private val retrieveTicket = RetrieveTicket(
        listOf(firstIntegration, secondIntegration)
    )

    @Test
    fun `should return retrieve with success when ticket have smallest price`() {
        val integrationInput = AirLaneTicketIntegrationInput(
            origin = "ORIGIN",
            destination = "DESTINATION",
            date = "10/11/2023"
        )

        val retrieveTicketInput = RetrieveTicketInput(
            origin = "ORIGIN",
            destination = "DESTINATION",
            date = "10/11/2023"
        )
        val firstIntegrationResponse = IntegrationOutput.IntegrationSuccess(
            data = AirlineTicketIntegrationOutput(
                companyName = "GOL",
                departureDate = "10/11/2023",
                lowestPrice = 100.0,
                originAirport = "ORIGIN",
                destinationAirport = "DESTINATION"
            )
        )

        val secondIntegrationResponse = IntegrationOutput.IntegrationSuccess(
            data = AirlineTicketIntegrationOutput(
                companyName = "LATAM",
                departureDate = "10/11/2023",
                lowestPrice = 150.0,
                originAirport = "ORIGIN",
                destinationAirport = "DESTINATION"
            )
        )

        every {
            firstIntegration.integrate(integrationInput)
        } returns firstIntegrationResponse

        every {
            secondIntegration.integrate(integrationInput)
        } returns secondIntegrationResponse

        val expected = RetrieveTicketOutput.RetrieveTicketSuccess(
            ticket = Ticket(
                company = Company("GOL"),
                origin = Airport("ORIGIN"),
                destination = Airport("DESTINATION"),
                date = "10/11/2023",
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
