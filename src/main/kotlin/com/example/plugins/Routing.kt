package com.example.plugins

import com.lordcodes.turtle.shellRun
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/api/sd") {
            val cal = shellRun("date")
            println(cal)
            call.respondText(cal, ContentType.parse("text/plain"))
        }
    }
}
