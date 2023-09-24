package com.github.ata.integration.ticket.latam.extractor

import com.github.ata.integration.exception.ExtractorException
import com.github.ata.integration.http.dto.StepResponse
import com.github.ata.integration.ticket.latam.dto.LatamTicketRequest
import com.github.ata.integration.ticket.latam.dto.LatamTicketResponse
import com.github.ata.integration.ticket.latam.steps.GetCookiesStep
import com.github.ata.integration.ticket.latam.steps.GetTicketsStep
import com.github.ata.shared.extension.StringExtensions.convertTo

class LatamTicketExtractor(
    private val getCookiesStep: GetCookiesStep,
    private val getTicketsStep: GetTicketsStep
) {

    fun extract(request: LatamTicketRequest): LatamTicketResponse {
        val cookies = when (val response = getCookiesStep.doRequest()) {
            is StepResponse.StepSuccess -> response.getHeaderByKey(SET_COOKIE_KEY)
            else -> throw ExtractorException("Error when extracte response from getCookiesStep $response")
        }

        return when (val response = getTicketsStep.doRequest(request, cookies)) {
            is StepResponse.StepSuccess -> response.payload.convertTo()
            else -> throw ExtractorException("Error when extracte response from getTicketsStep $response")
        }
    }

    companion object {
        const val SET_COOKIE_KEY = "Set-Cookie"
    }
}