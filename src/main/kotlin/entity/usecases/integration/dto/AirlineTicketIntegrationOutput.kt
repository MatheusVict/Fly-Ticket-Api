package entity.usecases.integration.dto

import entity.ticket.Airport
import entity.ticket.Company
import entity.ticket.Ticket

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
