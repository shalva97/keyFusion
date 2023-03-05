package com.codecraft.ai.api.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Text2ImageParams(
    val uuid: String = UUID.randomUUID().toString(),
    val prompt: String,
    val steps: Int = 20,
    val width: Int = 512,
    val height: Int = 512,
)