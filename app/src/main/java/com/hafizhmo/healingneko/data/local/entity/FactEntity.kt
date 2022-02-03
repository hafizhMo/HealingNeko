package com.hafizhmo.healingneko.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fact_table")
data class FactEntity(
    val fact: String,
    @PrimaryKey val id: Int? = null
)