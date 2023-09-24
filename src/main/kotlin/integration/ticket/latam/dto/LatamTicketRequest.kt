package integration.ticket.latam.dto

import usecases.integration.dto.AirlineTicketIntegrationInput

class LatamTicketRequest (
    val origin: String,
    val destination: String,
    val outFrom: String
) {
    companion object {
        fun fromAirlineTicketInput(input: AirlineTicketIntegrationInput) = LatamTicketRequest(
            origin = input.origin,
            destination = input.destination,
            outFrom = input.date
        )
    }
}