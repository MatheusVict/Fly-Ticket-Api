package entity.usecases.integration.dto

data class AirLaneTicketIntegrationInput(
    val origin: String,
    val destination: String,
    val date: String
)
