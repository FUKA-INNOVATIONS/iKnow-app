package com.fuka.iknow.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BroadcastAction(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val value: String,
    val type: String,
    val timestamp: Long
)