package com.codecraft.ai.api.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("This Is Key fusion API (Host of Stable Diffusion!) V=1.0.0")
        }
    }
    keyFusionAPI()
}
