package com.github.ata.usecases.integration

import com.github.ata.usecases.integration.dto.AirlineTicketIntegrationInput
import com.github.ata.usecases.integration.dto.IntegrationOutput

interface AirlineTicketIntegration {
    fun integrate(input: AirlineTicketIntegrationInput): IntegrationOutput
}