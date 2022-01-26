package com.hafizhmo.healingneko.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Fact::class],
    version = 1
)
abstract class FactDatabase: RoomDatabase() {
    abstract fun factDao(): FactDao
}