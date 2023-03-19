package com.codecraft.ai.api.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Text2ImageRequest(
    val uuid: String = UUID.randomUUID().toString(),
    val prompt: String,
    val steps: Int = 20,
    val width: Int = 512,
    val height: Int = 512,
    val batch_size: Int = 1,
    val cfg_scale: Double = 7.0,
    val denoising_strength: Int = 0,
    val enable_hr: Boolean = false,
    val eta: Double? = 0.0,
    val firstphase_height: Int = 0,
    val firstphase_width: Int = 0,
    val hr_resize_x: Int = 0,
    val hr_resize_y: Int = 0,
    val hr_scale: Double = 2.0,
    val hr_second_pass_steps: Int = 0,
    val hr_upscaler: String? = null,
    val n_iter: Int = 1,
    val negative_prompt: String? = null,
    val override_settings_restore_afterwards: Boolean = true,
    val restore_faces: Boolean = false,
    val s_churn: Double? = 0.0,
    val s_noise: Double? = 1.0,
    val s_tmax: Double? = 0.0,
    val s_tmin: Double? = 0.0,
    val sampler_index: String = "Euler",
    val sampler_name: String? = null,
    val script_name: String? = null,
    val seed: Int = -1,
    val seed_resize_from_h: Int = -1,
    val seed_resize_from_w: Int = -1,
    val styles: List<String>? = null,
    val subseed: Int = -1,
    val subseed_strength: Int = 0,
    val tiling: Boolean = false,
)