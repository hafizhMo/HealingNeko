package com.hafizhmo.healingneko.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hafizhmo.healingneko.data.local.dao.FactDao
import com.hafizhmo.healingneko.data.local.entity.FactEntity

@Database(entities = [FactEntity::class], version = 2)
abstract class FactDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao
}