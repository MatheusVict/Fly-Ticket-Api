package com.github.ata.integration.http.dto

import java.lang.Exception

data class ClientHttpResponse(
    val statusCode: Int,
    val body: String?,
    val headers: Map<String, List<String>>
) {
    fun toStepResponse() = if (statusCode in 200..299) {
        val body = getNotNullBody()
        StepResponse.StepSuccess(payload = body ?: "", headers = headers)
    } else {
        StepResponse.StepFailure(payload = body ?: "")
    }

    private fun getNotNullBody() = body ?: throw Exception("Body is null")
}
