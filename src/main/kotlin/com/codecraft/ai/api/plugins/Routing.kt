package com.codecraft.ai.api.plugins

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO
import com.lordcodes.turtle.shellRun
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World By CodeCraft!")
            //go() //Disabling temporarily
        }
        get("/api/sd") {

        }
        static("img") {
            staticRootFolder = File("C:\\SD_Dir")
            files(".")
        }
        get("/api/sd") {
            val cal = shellRun("date")
            println(cal)
            call.respondText(cal, ContentType.parse("text/plain"))
        }
    }
}


fun go() {
    val url = "http://127.0.0.1:7860"
    val client = OkHttpClient()
    val mediaType = "application/json; charset=utf-8".toMediaType()

    val payload = JSONObject().apply {
        put("prompt", "Si-Fi space battle car, black background, digital art")
        put("steps", 20)
    }


    val request = Request.Builder()
        .url("$url/sdapi/v1/txt2img")
        .post(RequestBody.create(mediaType, payload.toString()))
        .build()

    val response = client.newCall(request).execute()
    val responseBody = response.body!!.string()


    val convertedObject = Gson().fromJson(responseBody, JsonObject::class.java)

    val images = convertedObject.getAsJsonArray("images")

    val path = "/SD_Dir/${UUID.randomUUID()}"
    Files.createDirectories(Paths.get(path))

    for (i in 0 until images.size()) {
        val img = images.get(i)
        var data = img.toString().split(",")[0].replaceFirst("\"", "")
        data = data.substring(0, data.length - 1)

        val decodedImage = Base64.getDecoder().decode(data.toByteArray(StandardCharsets.UTF_8))
        val bufferedImage = ImageIO.read(ByteArrayInputStream(decodedImage))

        ImageIO.write(bufferedImage, "png", File("$path/Image_By_CodeCraft_SD.png")) //TODO save in appropriate place
    }

    println("Done")


}
