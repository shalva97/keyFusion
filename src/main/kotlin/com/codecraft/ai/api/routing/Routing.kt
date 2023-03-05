package com.codecraft.ai.api.routing

import com.codecraft.ai.api.models.Text2ImageParams
import com.lordcodes.turtle.shellRun
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Duration
import java.util.*
import javax.imageio.ImageIO

fun Application.configureRouting() {
    routing {
        get("/") {
            val start = System.currentTimeMillis()
            println("request received => [$start]")
            call.respondText("Hello World By CodeCraft!")
            //go(start) //Disabling temporarily
            connectToSDAndGenerateImage(Text2ImageParams(prompt = ""))
        }
        static("img") {
            staticRootFolder = File("C:\\SD_Dir")
            files(".")
        }
        post("/generateImage") {
            val postParams = call.receive<String>()
            println("Received json => $postParams")
            call.respond(Text2ImageParams(prompt = "Some prompt"))
        }
    }
    sdAPI()
}


suspend fun connectToSDAndGenerateImage(text2ImageParams: Text2ImageParams) {
    val client = HttpClient(CIO){
        install(HttpTimeout){
            connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }
    }

    val response = client.post("http://127.0.0.1:7860") {
        contentType(ContentType.Application.Json)
        setBody(text2ImageParams)
    }

    Json.decodeFromString<Text2ImageParams>(response.body())


    val images = convertedObject.getAsJsonArray("images")

    val path = "/SD_Dir/${uuid}"
    Files.createDirectories(Paths.get(path))

    for (i in 0 until images.size()) {
        val img = images.get(i)
        var data = img.toString().split(",")[0].replaceFirst("\"", "")
        data = data.substring(0, data.length - 1)

        val decodedImage = Base64.getDecoder().decode(data.toByteArray(StandardCharsets.UTF_8))
        val bufferedImage = ImageIO.read(ByteArrayInputStream(decodedImage))

        ImageIO.write(bufferedImage, "png", File("$path/Image_By_CodeCraft_SD.png")) //TODO save in appropriate place

        //TODO we should send this url back to user:  http://127.0.0.1:8080/img/5a5b7fb1-853b-4d5f-aa3a-a56741a16946/Image_By_CodeCraft_SD.png
        //http://127.0.0.1:8080 this is standard url
        //endpoint is: /img
        //uuid: 5a5b7fb1-853b-4d5f-aa3a-a56741a16946
        //Image_By_CodeCraft_SD.png     this is name fo the image, we can append it or even create image with this url and we do not need to append enithing
    }

    println("Done for [$uuid] ts[${System.currentTimeMillis()}]")
    val seconds = (System.currentTimeMillis() - start)/1000
    println("Request finished in [$seconds]s")
    //For example roughly if 30 request are queued it takes 30 seconds so if 100 request are queued it will take 16 min approximately
    //well if we look that way it seems slow, but let's see how much request we will get maybe with received money we will buy new
    //server, and we will create load balancer and so on. at some point our demand will meet our server hardware and in that point
    //we can use money for other staff, and so on. let's see future will tell

}
