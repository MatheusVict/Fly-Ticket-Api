package com.github.ata.integration.ticket.latam

import com.github.ata.integration.ticket.latam.dto.LatamTicketRequest.Companion.fromAirlineTicketInput
import com.github.ata.integration.ticket.latam.extractor.LatamTicketExtractor
import com.github.ata.shared.extension.getLogger
import com.github.ata.usecases.integration.AirlineTicketIntegration
import com.github.ata.usecases.integration.dto.AirlineTicketIntegrationInput
import com.github.ata.usecases.integration.dto.IntegrationOutput
import com.github.ata.usecases.integration.dto.IntegrationOutput.IntegrationSuccess
import com.github.ata.usecases.integration.dto.IntegrationOutput.IntegrationFailure

class LatamTicketIntegration(
    private val latamTicketExtractor: LatamTicketExtractor
) : AirlineTicketIntegration {
    override fun integrate(input: AirlineTicketIntegrationInput): IntegrationOutput {
        return try {
            val extractorResponse = latamTicketExtractor.extract(
                request = fromAirlineTicketInput(input)
            )
            IntegrationSuccess(
                data = extractorResponse.toAirlineTicketOutput()
            )
        } catch (e: Exception) {
            logger.error(e.message)
           IntegrationFailure(
                e.message ?: "Error when integrate with Latam API"
            )
        }
    }

    companion object {
        val logger = getLogger()
    }
}