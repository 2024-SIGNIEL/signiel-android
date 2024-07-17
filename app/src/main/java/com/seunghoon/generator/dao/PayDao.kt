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

    @Query("select * from pay where year = :year and month = :month and day = :day")
    fun queryTodayPay(
        year: Int,
        month: Int,
        day: Int,
    ): List<Pay>

    @Query("select * from pay where year = :year and month = :month order by day asc")
    fun queryByMonthValue(
        year: Int,
        month: Int,
    ): List<Pay>
}
