package com.codecraft.ai.api.routing

import com.codecraft.ai.api.models.Text2ImgParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.sdAPI() {
    routing {
        get("/generateImage") {
            val postParams = call.receive<String>()
            call.request.queryParameters.forEach { s, strings ->
                println(s)
                println(strings)
            }
            call.respond(Text2ImgParams("Nodar"))
        }
    }
}