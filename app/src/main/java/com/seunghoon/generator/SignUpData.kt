package com.seunghoon.generator

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class SignUpData(
    val name: String = "",
    val password: String = "",
    val emailCode: String = "",
    val email: String = "",
)

internal fun SignUpData.toJsonString() = Json.encodeToString(this)
internal fun String.toSignUpData() = Json.decodeFromString<SignUpData>(this)
