package com.seunghoon.generator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pay(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val payType: PayType,
    val amount: Int,
    val use: String,
    val year: String,
    val month: String,
    val day: String,
)

enum class PayType(val value: String) {
    DEPOSIT("입금"),
    WITHDRAWAL("출금"),
}
