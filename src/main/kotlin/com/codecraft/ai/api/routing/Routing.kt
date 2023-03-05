package com.codecraft.ai.api.routing

import com.codecraft.ai.api.engine.GREETINGS
import com.codecraft.ai.api.engine.startText2ImageProcess
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText(GREETINGS)
        }
        get("/keyfusion/v1/text2image") {
            startText2ImageProcess(call)
        }
    }
}
