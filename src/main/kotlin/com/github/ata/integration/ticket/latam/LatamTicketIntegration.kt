package com.github.ata.integration.ticket.latam

import com.github.ata.usecases.integration.AirlineTicketIntegration
import com.github.ata.usecases.integration.dto.AirlineTicketIntegrationInput
import com.github.ata.usecases.integration.dto.AirlineTicketIntegrationOutput
import com.github.ata.usecases.integration.dto.IntegrationOutput

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