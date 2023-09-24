package integration.ticket.latam.extractor

import integration.exception.ExtractorException
import integration.http.dto.StepResponse
import integration.ticket.latam.dto.LatamTicketRequest
import integration.ticket.latam.dto.LatamTicketResponse
import integration.ticket.latam.steps.GetCookiesStep
import integration.ticket.latam.steps.GetTicketsStep
import shared.extension.StringExtensions.convertTo

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
            is StepResponse.StepSuccess -> response.payload.convertTo<LatamTicketResponse>()
            else -> throw ExtractorException("Error when extracte response from getTicketsStep $response")
        }
    }

    companion object {
        const val SET_COOKIE_KEY = "Set-Cookie"
    }
}