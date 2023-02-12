package com.fuka.iknow.viewModels

import android.app.Application
import androidx.lifecycle.*

import com.fuka.iknow.data.database.dataBase.iKnowDatabase;
import com.fuka.iknow.data.database.entity.BroadcastAction;

import java.time.Instant;
import java.time.ZoneOffset;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter.ofPattern


class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private val db = iKnowDatabase.get_iKnowDatabase(application)

    // Gets all of the BroadcastActions
    fun getBroadcastActions(): LiveData<List<BroadcastAction>> {
        return db.iKnowDao.getBroadcastActions()
    }

    // Gets BroadcastAction by ID
    fun getBroadcastActionByID(id: Long): LiveData<BroadcastAction> {
        return db.iKnowDao.getBroadcastActionByID(id)
    }

    // Gets all BroadcastActions by type
    fun getBroadcastActionsByType(type: String): LiveData<List<BroadcastAction>> {
        return db.iKnowDao.getBroadcastActionsByType(type)
    }

    // Adds one BroadcastAction
    fun addBroadcastAction(broadcastAction: BroadcastAction) {
        viewModelScope.launch(Dispatchers.IO) {
            db.iKnowDao.insertOrUpdateBroadcastAction(broadcastAction)
        }
    }

    // For testing // Adds defined amount of default BroadcastActions
    fun addBroadcastActions() {
        var timestamp: String

        viewModelScope.launch(Dispatchers.IO) {

            for (i in 1..1) {
                timestamp = ofPattern("dd-MM-yyyy HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())

                db.iKnowDao.insertOrUpdateBroadcastAction(
                    BroadcastAction(
                        0,
                        "Video captured",
                        "Camera",
                        timestamp
                    )
                )
            }
        }
    }
}