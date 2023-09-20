package entity.usecases.retrieve.dto

import entity.usecases.integration.dto.AirLaneTicketIntegrationInput

data class RetrieveTicketInput(
    val origin: String,
    val destination: String,
    val date: String
) {
    fun toAirlineTicketIntegrationInput() = AirLaneTicketIntegrationInput(
        origin = origin,
        destination = destination,
        date = date
    )
}
