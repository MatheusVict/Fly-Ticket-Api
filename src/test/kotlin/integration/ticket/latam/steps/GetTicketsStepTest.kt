package integration.ticket.latam.steps

import com.github.ata.integration.http.ConnectionHttpClient
import com.github.ata.integration.http.dto.ClientHttpResponse
import com.github.ata.integration.http.dto.StepResponse
import com.github.ata.integration.ticket.latam.dto.Constants.BASE_SERVICE_URL
import com.github.ata.integration.ticket.latam.dto.LatamTicketRequest
import com.github.ata.integration.ticket.latam.steps.GetTicketsStep
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import java.net.HttpURLConnection
import kotlin.test.Test

class GetTicketsStepTest {
    private val httpClient: ConnectionHttpClient = mockk()
    private val step = GetTicketsStep(httpClient)

    @Test
    fun `should return StepSuccess when execute step successfully`() {
        val endpoint = "bff/air-offers/offers/search?"

        val headers = mapOf(
            "Cookies" to listOf("cookie1", "cookie2")
        )

        every {
            httpClient.get(
                url = BASE_SERVICE_URL + endpoint + queryParams,
                headers = requestHeaders
            )
        } returns ClientHttpResponse(
            statusCode = HttpURLConnection.HTTP_OK,
            body = "Success response",
            headers = headers
        )

        val expected = StepResponse.StepSuccess(
            payload = "Success response",
            headers = headers
        )

        val actual = step.doRequest(
            request = LatamTicketRequest(
                origin = "origin",
                destination = "destination",
                outFrom = "01/01/2023"
            ),
            cookies = "cookie1"
        )

        assertEquals(expected, actual)
    }


    @Test
    fun `should return StepFailure when execute step successfully`() {
        val endpoint = "bff/air-offers/offers/search?"

        val headers = mapOf(
            "Cookies" to listOf("cookie1", "cookie2")
        )

        every {
            httpClient.get(
                url = BASE_SERVICE_URL + endpoint + queryParams,
                headers = requestHeaders
            )
        } returns ClientHttpResponse(
            statusCode = HttpURLConnection.HTTP_BAD_REQUEST,
            body = "Error response",
            headers = headers
        )

        val expected = StepResponse.StepFailure(
            payload = "Error response"
        )

        val actual = step.doRequest(
            request = LatamTicketRequest(
                origin = "origin",
                destination = "destination",
                outFrom = "01/01/2023"
            ),
            cookies = "cookie1"
        )

        assertEquals(expected, actual)
    }

    companion object {
        const val queryParams = "sort=PRICE%2Casc" +
                "&cabinType=Economy" +
                "&origin=origin" +
                "&destination=destination" +
                "&inFlightDate=null" +
                "&inFrom=null" +
                "&inOfferId=null" +
                "&outFlightDate=null" +
                "&outFrom=01/01/2023" +
                "&outOfferId=null" +
                "&adult=1" +
                "&child=0" +
                "&infant=0" +
                "&redemption=false"

        val requestHeaders = mapOf(
            "x-latam-app-session-id" to "1",
            "content-type" to "application/json",
            "x-latam-action-name" to ":search-result.flightselection.offers-search",
            "x-latam-application-name" to ":web-air-offers",
            "x-latam-client-name" to ":web-air-offers",
            "x-latam-track-id" to "1",
            "x-latam-request-id" to "1",
            "x-latam-application-country" to "BR",
            "x-latam-application-oc" to "br",
            "x-latam-application-lang" to "pt",
            "sec-fetch-dest" to "empty",
            "sec-fetch-mode" to "cors",
            "sec-fetch-site" to "same-origin",
            "te" to "trailers",
            "cookie" to "cookie1"
        )
    }
}