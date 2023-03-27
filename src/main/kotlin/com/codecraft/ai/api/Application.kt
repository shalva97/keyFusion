package com.codecraft.ai.api

import com.codecraft.ai.api.plugins.configureAdministration
import com.codecraft.ai.api.plugins.configureMonitoring
import com.codecraft.ai.api.plugins.configureSerialization
import com.codecraft.ai.api.plugins.configureTemplating
import com.codecraft.ai.api.routing.configureRouting
import io.ktor.network.tls.certificates.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import java.io.File

fun main() {


    val keyStoreFile = File("build/keystore.jks")
    val keyStore = buildKeyStore {
        certificate("sampleAlias") {
            password = "foobar"
            domains = listOf("192.168.86.26")
        }
    }
    keyStore.saveToFile(keyStoreFile, "123456")

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            port = 8080
            host = "192.168.86.26"
        }
        sslConnector(
            keyStore = keyStore,
            keyAlias = "sampleAlias",
            keyStorePassword = { "123456".toCharArray() },
            privateKeyPassword = { "foobar".toCharArray() }) {
            port = 8443
            host = "192.168.86.26"
            keyStorePath = keyStoreFile
        }
        module(Application::module)
    }

    embeddedServer(Netty, environment).start(wait = true)

    /*embeddedServer(
        Netty,
        port = 1701,
        host = "192.168.100.17",
        module = Application::module
    ).start(wait = true)*/
}

fun Application.module() {
    configureAdministration()
    configureTemplating()
    configureSerialization()
    configureMonitoring()
//    configureSecurity()
    configureRouting()
}
