package com.seunghoon.generator.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pay(
    @PrimaryKey val id: Long,
    val payType: PayType,
    val amount: Int,
    val use: String,
)

enum class PayType(val value: String) {
    DEPOSIT("입금"),
    WITHDRAWAL("출금"),
}
