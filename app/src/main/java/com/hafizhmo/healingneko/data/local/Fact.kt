package com.hafizhmo.healingneko.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fact(
    val fact: String,
    @PrimaryKey val id: Int? = null
)