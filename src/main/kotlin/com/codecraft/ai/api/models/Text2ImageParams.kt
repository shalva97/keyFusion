package com.codecraft.ai.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Text2ImageParams(
    val prompt: String,
    val steps: Int?,
    val width: Int?,
    val height: Int?,
)