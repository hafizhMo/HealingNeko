package com.hafizhmo.healingneko.data.local.dao

import androidx.room.*
import com.hafizhmo.healingneko.data.local.entity.FactEntity

@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(vararg fact: FactEntity)

    @Delete
    suspend fun deleteFact(fact: FactEntity)

    @Query("SELECT * FROM fact_table")
    suspend fun getAllFact(): List<FactEntity>
}