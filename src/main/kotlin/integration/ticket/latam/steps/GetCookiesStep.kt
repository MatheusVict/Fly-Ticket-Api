package integration.ticket.latam.steps

import integration.http.ConnectionHttpClient
import integration.http.dto.StepResponse
import integration.ticket.latam.dto.Constants.BASE_SERVICE_URL

class GetCookiesStep(
    private val httpClient: ConnectionHttpClient
) {
    fun doRequest(): StepResponse {
        return httpClient.get(BASE_SERVICE_URL).toStepResponse()
    }
}