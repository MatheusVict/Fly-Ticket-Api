package integration.ticket.latam

import usecases.integration.AirlineTicketIntegration
import usecases.integration.dto.AirlineTicketIntegrationInput
import usecases.integration.dto.AirlineTicketIntegrationOutput
import usecases.integration.dto.IntegrationOutput

class LatamTicketIntegration: AirlineTicketIntegration {
    override fun integrate(input: AirlineTicketIntegrationInput): IntegrationOutput {
        return IntegrationOutput.IntegrationSuccess(
            data = AirlineTicketIntegrationOutput(
                companyName = "companyName",
                departureDate = "",
                lowestPrice = 10.0,
                originAirport = "",
                destinationAirport = ""
            )
        )
    }
}