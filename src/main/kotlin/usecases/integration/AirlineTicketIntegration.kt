package usecases.integration

import usecases.integration.dto.AirlineTicketIntegrationInput
import usecases.integration.dto.IntegrationOutput

interface AirlineTicketIntegration {
    fun integrate(input: AirlineTicketIntegrationInput): IntegrationOutput
}