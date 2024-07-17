package com.seunghoon.generator.entity

import kotlinx.serialization.Serializable

@Serializable
data class GptRequest (
    val prompt: String,
)
