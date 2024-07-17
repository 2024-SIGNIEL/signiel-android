package com.seunghoon.generator.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val id: Long,
    val accessToken: String,
    val expiredAt: String,
)
