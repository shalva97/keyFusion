package com.codecraft.ai.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Text2ImageParams(
    val prompt: String,
    val steps: Int = 20,
    val width: Int = 512,
    val height: Int = 512,
)