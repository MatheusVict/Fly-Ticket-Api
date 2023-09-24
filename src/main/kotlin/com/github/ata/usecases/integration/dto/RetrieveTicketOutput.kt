package com.github.ata.usecases.integration.dto

import com.github.ata.entity.ticket.Ticket

sealed class RetrieveTicketOutput {
    data class RetrieveTicketSuccess(val ticket: Ticket): RetrieveTicketOutput()

    data class RetrieveTicketFailure(val message: String): RetrieveTicketOutput()
}