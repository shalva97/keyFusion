package com.codecraft.ai.api.routing

import com.codecraft.ai.api.models.Text2ImageParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.sdAPI() {
    routing {
        get("/generateImage") {
            call.request.apply {
                val response = Text2ImageParams(
                    queryParameters["prompt"] ?: return@get call.respond(
                        HttpStatusCode(
                            418,
                            "I'm a tea pot"
                        )
                    ),
                    queryParameters["height"]?.toInt(),
                    queryParameters["steps"]?.toInt(),
                    queryParameters["width"]?.toInt()
                )
                println(response)
                call.respond(
                    response
                )
            }
        }
    }
}