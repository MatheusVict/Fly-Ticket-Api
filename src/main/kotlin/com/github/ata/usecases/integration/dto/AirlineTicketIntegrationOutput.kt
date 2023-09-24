package com.github.ata.usecases.integration.dto

import com.github.ata.entity.ticket.Airport
import com.github.ata.entity.ticket.Company
import com.github.ata.entity.ticket.Ticket

data class AirlineTicketIntegrationOutput(
    val companyName: String,
    val departureDate: String,
    val lowestPrice: Double,
    val originAirport: String,
    val destinationAirport: String
) {
    fun toEntity() = Ticket(
        company = Company(companyName),
        origin = Airport(originAirport),
        destination = Airport(destinationAirport),
        date = departureDate,
        price = lowestPrice

    )
}
