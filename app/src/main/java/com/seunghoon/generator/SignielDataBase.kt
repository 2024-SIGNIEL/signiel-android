package com.seunghoon.generator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seunghoon.generator.dao.PayDao
import com.seunghoon.generator.entity.Pay

@Database(
    version = 2,
    entities = [Pay::class],
)
abstract class SignielDatabase : RoomDatabase() {
    abstract fun getPayDao(): PayDao
}
