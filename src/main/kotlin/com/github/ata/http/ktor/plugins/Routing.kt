package com.github.ata.http.ktor.plugins

import com.github.ata.http.ktor.controller.healthCheck
import com.github.ata.http.ktor.controller.retrieveSolicitations
import com.github.ata.usecases.solicitation.SolicitationHandler
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val solicitationHandler by inject<SolicitationHandler>()
    install(Locations)

    routing {
        healthCheck()
        retrieveSolicitations(solicitationHandler)
    }
}