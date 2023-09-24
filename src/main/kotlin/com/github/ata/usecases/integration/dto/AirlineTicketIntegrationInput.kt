package com.github.ata.usecases.integration.dto

data class AirlineTicketIntegrationInput(
    val origin: String,
    val destination: String,
    val date: String
)
