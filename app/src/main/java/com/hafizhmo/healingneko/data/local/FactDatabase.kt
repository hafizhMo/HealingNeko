package com.hafizhmo.healingneko.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Fact::class],
    version = 1
)
abstract class FactDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao

    companion object {
        @Volatile
        private var INSTANCE: FactDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FactDatabase {
            if (INSTANCE == null) {
                synchronized(FactDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FactDatabase::class.java,
                        "fact-db"
                    ).build()
                }
            }
            return INSTANCE as FactDatabase
        }
    }
}