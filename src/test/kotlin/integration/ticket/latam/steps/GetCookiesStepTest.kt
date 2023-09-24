package integration.ticket.latam.steps

import com.github.ata.integration.ticket.latam.steps.GetCookiesStep
import com.github.ata.integration.http.ConnectionHttpClient
import com.github.ata.integration.http.dto.ClientHttpResponse
import com.github.ata.integration.http.dto.StepResponse
import com.github.ata.integration.ticket.latam.dto.Constants.BASE_SERVICE_URL
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import java.net.HttpURLConnection
import kotlin.test.Test

class GetCookiesStepTest {
    private val httpClient: ConnectionHttpClient = mockk()
    private val step = GetCookiesStep(httpClient)

    //success StepSuccess
    @Test
    fun `should return StepSuccess when execute step successfully`() {
        val headers = mapOf(
            "Cookies" to listOf("cookie1", "cookie2")
        )

        every {
            httpClient.get(BASE_SERVICE_URL)
        } returns ClientHttpResponse(
            statusCode = HttpURLConnection.HTTP_OK,
            body = "Success response",
            headers = headers
        )

        val expected = StepResponse.StepSuccess(
            payload = "Success response",
            headers = headers
        )

        val actual = step.doRequest()

        assertEquals(expected, actual)
    }

    //failure StepFailure
    @Test
    fun `should return StepFailure when not execute step successfully`() {
        every {
            httpClient.get(BASE_SERVICE_URL)
        } returns ClientHttpResponse(
            statusCode = HttpURLConnection.HTTP_BAD_REQUEST,
            body = "Error response",
            headers = emptyMap()
        )

        val expect = StepResponse.StepFailure(
            payload = "Error response"
        )

        val actual = step.doRequest()

        assertEquals(expect, actual)

    }
}