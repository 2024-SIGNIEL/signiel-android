package com.seunghoon.generator

import android.content.Context
import android.content.SharedPreferences

object Keys {
    const val MAX_PAY = "max_pay"
    const val PIN_MONEY = "pin_money"
}

object SharedPreferenceManager {
    lateinit var sharedPreference: SharedPreferences

    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences("Signiel", Context.MODE_PRIVATE)
    }
}
