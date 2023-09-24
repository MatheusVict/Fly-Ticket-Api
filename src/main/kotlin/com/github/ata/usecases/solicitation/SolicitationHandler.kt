package com.github.ata.usecases.solicitation

import com.github.ata.entity.ticket.Ticket
import com.github.ata.shared.extension.StringExtensions.convertTo
import com.github.ata.shared.file.FileUtils
import com.github.ata.shared.file.FileUtils.loadResource
import com.github.ata.usecases.integration.dto.RetrieveTicketOutput
import com.github.ata.usecases.integration.dto.RetrieveTicketOutput.RetrieveTicketSuccess
import com.github.ata.usecases.integration.dto.RetrieveTicketOutput.RetrieveTicketFailure
import com.github.ata.usecases.retrieve.RetrieveTicket
import com.github.ata.usecases.retrieve.dto.RetrieveTicketInput
import com.github.ata.usecases.solicitation.dto.SolicitationOutput
import com.github.ata.usecases.solicitation.dto.Summary

class SolicitationHandler(
    private val retrieveTicket: RetrieveTicket
) {
    fun retrieveAllSolicitations(): SolicitationOutput {
        val inputs = loadResource(SOLICITATIONS_RESOURCE_PATH).convertTo<List<RetrieveTicketInput>>()
        val retrieveOutputs = inputs.map { input -> retrieveTicket.retrieve(input) }

        return SolicitationOutput(
            cheapestTicket = getRetrievesSuccess(retrieveOutputs),
            summary = Summary(
                error = getRetrievesFailure(retrieveOutputs)
            )
        )
    }

    private fun getRetrievesSuccess(outputs: List<RetrieveTicketOutput>): List<Ticket> =
        outputs.asSequence()
            .filterIsInstance<RetrieveTicketSuccess>()
            .map { it.ticket }
            .toList()

    private fun getRetrievesFailure(outputs: List<RetrieveTicketOutput>): List<String> =
        outputs.asSequence()
            .filterIsInstance<RetrieveTicketFailure>()
            .map { it.message }
            .toList()

    companion object {
        private const val SOLICITATIONS_RESOURCE_PATH = "/solicitations/solicitations.json"
    }
}