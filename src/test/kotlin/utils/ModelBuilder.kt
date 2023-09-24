package utils

import entity.ticket.Airport
import entity.ticket.Company
import entity.ticket.Ticket
import integration.ticket.latam.dto.*
import usecases.integration.dto.AirlineTicketIntegrationInput
import usecases.integration.dto.AirlineTicketIntegrationOutput
import usecases.retrieve.dto.RetrieveTicketInput

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
    ) = AirlineTicketIntegrationInput(
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

    fun createLatamTicketRequest(
        origin: String = "FLN",
        destination: String = "GRU",
        outFrom: String = "2022-07-01",
    ) =
        LatamTicketRequest(
            origin = origin,
            destination = destination,
            outFrom = outFrom
        )

    fun createLatamTicketResponse() = LatamTicketResponse(
        content = listOf(createLatamContentResponse())
    )

    fun createLatamContentResponse(
        originIataCode: String = "FLN",
        destinationIataCode: String = "GRU",
        departure: String = "2022-07-01T18:40:00",
        amount: Double = 532.48
    ) = Content(
        summary = Summary(
            origin = Origin(
                departure = departure,
                iataCode = originIataCode
            ),
            destination = Destination(
                iataCode = destinationIataCode
            ),
            lowestPrice = LowestPrice(
                amount = amount
            )
        )
    )

}