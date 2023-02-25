package com.fuka.iknow.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BroadcastAction(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val value: String,
    val type: String,
    var status: Boolean,
    val timestamp: String
)