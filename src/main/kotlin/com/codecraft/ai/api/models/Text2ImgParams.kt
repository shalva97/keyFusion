package com.codecraft.ai.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Text2ImgParams(val prompt: String)
