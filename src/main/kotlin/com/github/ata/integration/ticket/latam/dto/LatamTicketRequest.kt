package com.github.ata.integration.ticket.latam.dto

import com.github.ata.shared.extension.StringExtensions.revertDate
import com.github.ata.usecases.integration.dto.AirlineTicketIntegrationInput

data class LatamTicketRequest (
    val origin: String,
    val destination: String,
    val outFrom: String
) {
    companion object {
        fun fromAirlineTicketInput(input: AirlineTicketIntegrationInput) = LatamTicketRequest(
            origin = input.origin,
            destination = input.destination,
            outFrom = input.date.revertDate()
        )
    }
}