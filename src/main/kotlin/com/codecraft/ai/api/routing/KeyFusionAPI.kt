package com.codecraft.ai.api.routing

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
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets
import java.util.*

fun Application.keyFusionAPI() {
    routing {
        get("/keyfusion/v1/text2image") {
            startText2ImageProcess(
                call,
                Text2ImageRequest(
                    steps = 25,
                    prompt = "single submarine, aircraft carrier, digital art, science fiction, in space, on top of mars, with f35 aircraft"
                ),
            )
        }
    }
}

suspend fun startText2ImageProcess(call: ApplicationCall, text2ImageRequest: Text2ImageRequest) {
    connectToSDAndGetImage(text2ImageRequest)?.let {
        call.respond(it)
    }
}

suspend fun connectToSDAndGetImage(text2ImageRequest: Text2ImageRequest): ByteArray? {
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

    val response: Text2ImageResponse = client.post("http://127.0.0.1:7860/sdapi/v1/txt2img") {
        contentType(ContentType.Application.Json)
        setBody(text2ImageRequest)
    }.body()

    response.images.firstOrNull()?.let {
        return Base64.getDecoder().decode(it.toByteArray(StandardCharsets.UTF_8))
    }
    return null
}




//TODO future development
/*
static("img") {
    staticRootFolder = File("C:\\SD_Dir")
    files(".")
}
post("/generateImage") {

    val postParams = call.receive<String>()
    println("Received json => $postParams")
    call.respond(Text2ImageRequest(prompt = "Some prompt"))
}

private suspend fun saveImageInMemory(text2ImageRequest: Text2ImageRequest, decodedImage: ByteArray) {
    val path = "/SD_Dir/${text2ImageRequest.uuid}"
    Files.createDirectories(Paths.get(path))

    val bufferedImage = ImageIO.read(ByteArrayInputStream(decodedImage))
    ImageIO.write(bufferedImage, "png", File("$path/Image_By_CodeCraft_SD.png"))
    //TODO we should send this url back to user:  http://127.0.0.1:8080/img/5a5b7fb1-853b-4d5f-aa3a-a56741a16946/Image_By_CodeCraft_SD.png
    //http://127.0.0.1:8080 this is standard url
    //endpoint is: /img
    //uuid: 5a5b7fb1-853b-4d5f-aa3a-a56741a16946
    //Image_By_CodeCraft_SD.png     this is name fo the image, we can append it or even create image with this url and we do not need to append enithing
}
*/