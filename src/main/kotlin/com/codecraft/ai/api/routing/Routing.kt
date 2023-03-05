package com.codecraft.ai.api.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val GREETINGS = "This Is Key fusion API (Host of Stable Diffusion!) V=1.0.0"

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText(GREETINGS)
        }
    }
    keyFusionAPI()
}
