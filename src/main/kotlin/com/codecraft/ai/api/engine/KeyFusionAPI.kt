package com.codecraft.ai.api.engine

import com.codecraft.ai.api.models.Text2ImageRequest
import com.codecraft.ai.api.models.Text2ImageResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets
import java.util.*

suspend fun startText2ImageProcess(call: ApplicationCall) {
    call.request.apply {
        val promptFromUser = queryParameters[QueryParam.PROMPT.value] ?: return Error.EMPTY_PROMPT.let {
            call.respond(
                HttpStatusCode(
                    it.code,
                    it.strRepresentation
                )
            )
        }

        var steps = queryParameters[QueryParam.STEPS.value]?.toInt() ?: 25
        steps = steps.coerceAtMost(25)

        var width = queryParameters[QueryParam.WIDTH.value]?.toInt() ?: 512
        width = width.coerceAtMost(512)

        var height = queryParameters[QueryParam.HEIGHT.value]?.toInt() ?: 512
        height = height.coerceAtMost(512)

        try {
            call.respond(
                connectToSDAndGetImage(
                    Text2ImageRequest(
                        prompt = promptFromUser,
                        steps = steps,
                        width = width,
                        height = height,
                    ),
                )
            )
        } catch (e: Exception) {
            Error.SD_PROBLEM.let {
                call.respond(
                    HttpStatusCode(it.code, it.strRepresentation)
                )
            }
        }

    }
}

suspend fun connectToSDAndGetImage(text2ImageRequest: Text2ImageRequest): ByteArray {
    val client = HttpClient(CIO) {
        install(HttpTimeout) {
            connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
    }

    val response: Text2ImageResponse = client.post(StableDiffusionLocalhost) {
        contentType(ContentType.Application.Json)
        setBody(text2ImageRequest)
    }.body()

    response.images.firstOrNull()?.let {
        return Base64.getDecoder().decode(it.toByteArray(StandardCharsets.UTF_8))
    } ?: throw Exception("Problem with Stable Diffusion Server")
}