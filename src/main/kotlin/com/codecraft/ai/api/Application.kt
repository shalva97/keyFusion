package com.codecraft.ai.api

import com.codecraft.ai.api.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureAdministration()
    configureTemplating()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
//    configureSecurity()
    configureRouting()
}
