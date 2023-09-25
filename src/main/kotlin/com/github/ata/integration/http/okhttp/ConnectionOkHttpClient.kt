package com.github.ata.integration.http.okhttp

import com.github.ata.integration.http.ConnectionHttpClient
import com.github.ata.integration.http.dto.ClientHttpResponse
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class ConnectionOkHttpClient : ConnectionHttpClient {
    override fun get(url: String, headers: Map<String, String>): ClientHttpResponse {
        val client = buildClient()
        val okHttpHears = buildHeaders(headers)

        val request = buildRequest(url, okHttpHears)

        val response = client.newCall(request).execute()
        return buildResponse(response)
    }


    private fun buildClient() =
        OkHttpClient()
            .newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .build()


    private fun buildHeaders(headers: Map<String, String>) = with(Headers.Builder()) {
        headers.forEach {
            add(it.key, it.value)
        }
        this.build()
    }

    private fun buildRequest(url: String, headers: Headers) = with(Request.Builder()) {
        this.url(url).headers(headers).build()
    }

    private fun buildResponse(response: Response) = ClientHttpResponse(
        headers = response.headers.toMultimap(),
        body = response.body?.string(),
        statusCode = response.code
    )
}