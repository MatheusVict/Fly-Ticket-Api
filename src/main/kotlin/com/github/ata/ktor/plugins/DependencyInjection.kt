package com.github.ata.ktor.plugins

import com.github.ata.integration.http.ConnectionHttpClient
import com.github.ata.integration.http.okhttp.ConnectionOkHttpClient
import com.github.ata.integration.ticket.latam.extractor.LatamTicketExtractor
import com.github.ata.integration.ticket.latam.steps.GetCookiesStep
import com.github.ata.integration.ticket.latam.steps.GetTicketsStep
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(appModule)
    }
}

val appModule = module(createdAtStart = true) {
    single<ConnectionHttpClient> { ConnectionOkHttpClient() }
    single { GetCookiesStep(get()) }
    single { GetTicketsStep(get()) }
    single { LatamTicketExtractor(get(), get()) }
}