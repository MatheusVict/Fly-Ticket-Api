package com.github.ata.http.ktor.plugins

import com.github.ata.http.ktor.controller.healthCheck
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Locations)

    routing {
        healthCheck()
    }
}