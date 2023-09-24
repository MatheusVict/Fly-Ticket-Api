package com.github.ata

import com.github.ata.http.ktor.plugins.configureDependencyInjection
import com.github.ata.http.ktor.plugins.configureRouting
import com.github.ata.http.ktor.plugins.configureSerialization
import com.github.ata.http.ktor.server.HttpServer
import io.ktor.server.application.*

fun main(args: Array<String>) {
    HttpServer.start(args)
}

fun Application.module() {
    configureDependencyInjection()
    configureSerialization()
    configureRouting()
}