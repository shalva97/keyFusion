package com.codecraft.ai.api

import com.codecraft.ai.api.plugins.configureAdministration
import com.codecraft.ai.api.plugins.configureMonitoring
import com.codecraft.ai.api.plugins.configureSerialization
import com.codecraft.ai.api.plugins.configureTemplating
import com.codecraft.ai.api.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        Netty,
        port = 1701,
        host = "192.168.100.17",
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    configureAdministration()
    configureTemplating()
    configureSerialization()
    configureMonitoring()
//    configureSecurity()
    configureRouting()
}
