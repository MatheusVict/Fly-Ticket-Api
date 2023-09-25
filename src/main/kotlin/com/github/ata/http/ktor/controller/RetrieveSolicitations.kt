package com.github.ata.http.ktor.controller

import com.github.ata.http.dto.response.RetrieveSolicitationResponses.Companion.fromSolicitationsOutput
import com.github.ata.usecases.solicitation.SolicitationHandler
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

@Location("/retrieve-solicitations")
class RetrieveSolicitations

fun Route.retrieveSolicitations(
    service: SolicitationHandler
) = get<RetrieveSolicitations> {
    val output = service.retrieveAllSolicitations()
    val response = fromSolicitationsOutput(output)
    call.respond(response)
}