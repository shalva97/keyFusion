package com.codecraft.ai.api.engine

const val GREETINGS = "This Is Key fusion API (Host of Stable Diffusion!) V=1.0.0"

const val StableDiffusionLocalhost = "http://127.0.0.1:7860/sdapi/v1/txt2img"

enum class QueryParam(val strRepresentation: String) {
    PROMPT("prompt"),
    STEPS("steps"),
    WIDTH("width"),
    HEIGHT("height"),
}

enum class Error(val code: Int, val strRepresentation: String) {
    EMPTY_PROMPT(418, "Prompt can not be empty")
}