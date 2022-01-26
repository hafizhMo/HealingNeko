package com.hafizhmo.healingneko.data.local

import androidx.room.*

@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFact(vararg fact: Fact)

    @Delete
    fun deleteFact(fact: Fact)

    @Query("SELECT * FROM fact")
    fun getAllFact(): List<Fact>
}