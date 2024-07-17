package com.seunghoon.generator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.seunghoon.generator.entity.Pay

@Dao
interface PayDao {
    @Insert
    fun savePay(pay: Pay)

    @Query("select * from pay")
    fun queryPay(): List<Pay>
}
