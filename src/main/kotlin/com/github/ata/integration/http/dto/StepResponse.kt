package com.github.ata.integration.http.dto

sealed class StepResponse {
    data class StepSuccess(val payload: String, val headers: Map<String, List<String>>) : StepResponse() {
        fun getHeaderByKey(key: String) =
            headers[key]?.joinToString() ?: throw IllegalArgumentException("Header $key not found")
    }

    data class StepFailure(val payload: String): StepResponse()
}