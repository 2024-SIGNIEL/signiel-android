package com.seunghoon.generator.entity

import kotlinx.serialization.Serializable

@Serializable
data class GptResponse (
    val detail: String,
)