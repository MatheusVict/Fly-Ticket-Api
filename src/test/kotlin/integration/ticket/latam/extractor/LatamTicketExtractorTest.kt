package integration.ticket.latam.extractor

import integration.exception.ExtractorException
import integration.http.dto.StepResponse.StepFailure
import integration.http.dto.StepResponse.StepSuccess
import integration.ticket.latam.steps.GetCookiesStep
import integration.ticket.latam.steps.GetTicketsStep
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shared.exception.ObjectConversionException
import utils.ModelBuilder.createLatamContentResponse
import utils.ModelBuilder.createLatamTicketRequest
import utils.TestUtils.getFileContent
import kotlin.test.assertEquals

class LatamTicketExtractorTest {
    private val getCookiesStep: GetCookiesStep = mockk()
    private val getTicketsStep: GetTicketsStep = mockk()
    private val latamTicketExtractor = LatamTicketExtractor(getCookiesStep, getTicketsStep)

    @Test
    fun `should extract latam ticket response successfully`() {
        val request = createLatamTicketRequest()
        val cookies = "Cookie1, Cookie2"
        val latamTicketsRequest = getFileContent("/tickets/latam_tickets_reponse.json")

        every { getCookiesStep.doRequest() } returns StepSuccess(
            payload = "SUCCESS",
            headers = mapOf(
                "Set-Cookie" to listOf("Cookie1", "Cookie2")
            )
        )

        every { getTicketsStep.doRequest(request, cookies) } returns StepSuccess(
            payload = latamTicketsRequest,
            headers = emptyMap()
        )

        val expected = createLatamContentResponse()
        val actual = latamTicketExtractor.extract(request).content.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `should throw ExtractorException when get cookies step returns an error`() {
        val request = createLatamTicketRequest()

        every { getCookiesStep.doRequest() } returns StepFailure("ERROR")

        assertThrows<ExtractorException> {
            latamTicketExtractor.extract(request)
        }
    }

    @Test
    fun `should throw IllegalArgumentException when get cookies step does not return valid headers`() {
        val request = createLatamTicketRequest()

        every { getCookiesStep.doRequest() } returns StepSuccess(
            payload = "SUCCESS",
            headers = mapOf("Invalid Key" to emptyList())
        )

        assertThrows<IllegalArgumentException> { latamTicketExtractor.extract(request) }
    }

    @Test
    fun `should throw ExtractorException when get tickets step return an error`() {
        val request = createLatamTicketRequest()
        val cookies = "Cookie1, Cookie2"
        val latamTicketsRequest = getFileContent("/tickets/latam_tickets_reponse.json")

        every { getCookiesStep.doRequest() } returns StepSuccess(
            payload = "SUCCESS",
            headers = mapOf(
                "Set-Cookie" to listOf("Cookie1", "Cookie2")
            )
        )

        every { getTicketsStep.doRequest(request, cookies) } returns StepFailure(
            payload = latamTicketsRequest
        )

        assertThrows<ExtractorException> { latamTicketExtractor.extract(request) }

    }


    @Test
    fun `should throw objectConversionException when get ticker step return a invalid response`() {
        val request = createLatamTicketRequest()
        val cookies = "Cookie1, Cookie2"

        every { getCookiesStep.doRequest() } returns StepSuccess(
            payload = "SUCCESS",
            headers = mapOf(
                "Set-Cookie" to listOf("Cookie1", "Cookie2")
            )
        )

        every { getTicketsStep.doRequest(request, cookies) } returns StepSuccess(
            payload = "INVALID RESPONSE",
            headers = emptyMap()
        )

        assertThrows<ObjectConversionException> { latamTicketExtractor.extract(request) }
    }
}