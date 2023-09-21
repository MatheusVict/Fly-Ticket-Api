package utils

import entity.ticket.Airport
import entity.ticket.Company
import entity.ticket.Ticket
import entity.usecases.integration.dto.AirLaneTicketIntegrationInput
import entity.usecases.integration.dto.AirlineTicketIntegrationOutput
import entity.usecases.retrieve.dto.RetrieveTicketInput

object ModelBuilder {

    fun createTicket(
        companyName: String = "Company",
        airportOrigin: String = "FLN",
        airportDestination: String = "GRU",
        date: String = "10/12/2023",
        price: Double = 100.0
    ) = Ticket(
        company = Company(companyName),
        origin = Airport(airportOrigin),
        destination = Airport(airportDestination),
        date = date,
        price = price
    )

    fun createAirlineTicketInput(
        origin: String = "FLN",
        destination: String = "GRU",
        date: String = "10/12/2023"
    ) = AirLaneTicketIntegrationInput(
        origin = origin,
        destination = destination,
        date = date
    )

    fun createRetrieveTicketInput(
        origin: String = "FLN",
        destination: String = "GRU",
        date: String = "10/12/2023"
    ) = RetrieveTicketInput(
        origin = origin,
        destination = destination,
        date = date
    )

    fun createAirlineTicketIntegrationOutput(
        companyName: String = "Company",
        departureDate: String = "10/12/2023",
        lowestPrice: Double = 100.0,
        originAirport: String = "FLN",
        destinationAirport: String = "GRU"
    ) = AirlineTicketIntegrationOutput(
        companyName = companyName,
        departureDate = departureDate,
        lowestPrice = lowestPrice,
        originAirport = originAirport,
        destinationAirport = destinationAirport
    )

}