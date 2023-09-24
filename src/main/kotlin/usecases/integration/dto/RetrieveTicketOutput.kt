package usecases.integration.dto

import entity.ticket.Ticket

sealed class RetrieveTicketOutput {
    data class RetrieveTicketSuccess(val ticket: Ticket): RetrieveTicketOutput()

    data class RetrieveTicketFailure(val message: String): RetrieveTicketOutput()
}