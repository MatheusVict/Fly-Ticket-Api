package entity.usecases.retrieve

import entity.ticket.Ticket
import entity.usecases.integration.AirlineTicketIntegration
import entity.usecases.integration.dto.IntegrationOutput
import entity.usecases.integration.dto.RetrieveTicketOutput
import entity.usecases.retrieve.dto.RetrieveTicketInput

class RetrieveTicket(
    private val integrations: List<AirlineTicketIntegration>
) {

    fun retrieve(input: RetrieveTicketInput): RetrieveTicketOutput {
        val integrationsResponses = integrations.map { integration ->
            integration.integrate(input.toAirlineTicketIntegrationInput())
        }
        val tickets = getSuccessIntegrations(integrationsResponses)
        val lowestTicketPrice = tickets.minByOrNull { it.price }

        return createOutput(input, lowestTicketPrice)
    }

    private fun getSuccessIntegrations(integrationsResponses: List<IntegrationOutput>): List<Ticket> {
        return integrationsResponses
            .asSequence()
            .filterIsInstance<IntegrationOutput.IntegrationSuccess>()
            .map { it.data.toEntity() }
            .toList()
    }

    private fun createOutput(input: RetrieveTicketInput, lowestTicketPrice: Ticket?): RetrieveTicketOutput {
        if (lowestTicketPrice == null) return RetrieveTicketOutput.RetrieveTicketFailure(
            message = "Could not extract ticket for ${input.origin} to ${input.destination}"
        )
        return RetrieveTicketOutput.RetrieveTicketSuccess(
            ticket = lowestTicketPrice
        )
    }
}