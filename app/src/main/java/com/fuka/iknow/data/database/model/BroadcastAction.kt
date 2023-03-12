package com.fuka.iknow.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


// Yksittäisen tapahtuman malli/kuvaus


@Entity
data class BroadcastAction(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val value: String,
    val type: String,
    var status: Boolean, // Käyttäjälle näytetyn notifikaation painike päivittää tätä kenttää
    val timestamp: String,
    val intentHashcode: Int
)