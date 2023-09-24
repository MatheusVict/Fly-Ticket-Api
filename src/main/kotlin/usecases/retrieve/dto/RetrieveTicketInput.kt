package usecases.retrieve.dto

import usecases.integration.dto.AirlineTicketIntegrationInput

data class RetrieveTicketInput(
    val origin: String,
    val destination: String,
    val date: String
) {
    fun toAirlineTicketIntegrationInput() = AirlineTicketIntegrationInput(
        origin = origin,
        destination = destination,
        date = date
    )
}
