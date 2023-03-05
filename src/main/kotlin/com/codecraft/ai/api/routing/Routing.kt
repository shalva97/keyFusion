package com.codecraft.ai.api.routing

import com.lordcodes.turtle.shellRun
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/") {
            val start = System.currentTimeMillis()
            println("request received => [$start]")
            call.respondText("Hello World By CodeCraft!")
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
    sdAPI()
}
