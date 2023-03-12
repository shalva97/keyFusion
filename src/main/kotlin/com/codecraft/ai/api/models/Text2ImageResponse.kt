package com.codecraft.ai.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Text2ImageResponse(
    val parameters: Text2ImageRequest,
    val images: List<String>,
    val info: String,
)