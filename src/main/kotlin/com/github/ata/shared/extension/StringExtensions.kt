package com.github.ata.shared.extension

import com.github.ata.shared.exception.ObjectConversionException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object StringExtensions {
    val logger = getLogger()

    inline fun <reified T : Any> String.convertTo(): T {
        try {
            val json = Json {
                ignoreUnknownKeys = true
            }
            return json.decodeFromString(this)
        } catch (e: Exception) {
            logger.error(e.message)
            throw ObjectConversionException("Could not convert ${e.message}")
        }
    }

    fun String.removeBreakLines() =
        this.trimIndent().replace("\n", "")

    fun String.timestampToSimpleDate(delimiter: String = "-", separator: String = "-") =
        this.split("T").first().split(delimiter).reversed().joinToString(separator)
}