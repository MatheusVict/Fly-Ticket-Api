package com.github.ata.usecases.integration.dto

sealed class IntegrationOutput {

    data class IntegrationSuccess(val data: AirlineTicketIntegrationOutput): IntegrationOutput()

    data class IntegrationFailure(val error: String): IntegrationOutput()
}