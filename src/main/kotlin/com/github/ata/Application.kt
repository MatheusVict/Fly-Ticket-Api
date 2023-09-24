package com.github.ata

import com.github.ata.ktor.plugins.configureDependencyInjection
import com.github.ata.ktor.server.HttpServer
import io.ktor.server.application.*

fun main(args: Array<String>) {
    HttpServer.start(args)
}

fun Application.module() {
    configureDependencyInjection()
}