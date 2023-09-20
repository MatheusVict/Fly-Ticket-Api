package entity.usecases.integration

import entity.usecases.integration.dto.AirLaneTicketIntegrationInput
import entity.usecases.integration.dto.AirlineTicketIntegrationOutput
import entity.usecases.integration.dto.IntegrationOutput

interface AirlineTicketIntegration {
    fun integrate(input: AirLaneTicketIntegrationInput): IntegrationOutput
}