package com.fuka.iknow.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.*
import com.fuka.iknow.data.database.dao.BroadcastActionDao

import com.fuka.iknow.data.database.dataBase.iKnowDatabase;
import com.fuka.iknow.data.database.model.BroadcastAction;
import com.fuka.iknow.data.database.repository.BroadcastActionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope

import java.time.Instant;
import java.time.ZoneOffset;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject


val TAG = "iKnow-app"
class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val db = iKnowDatabase.get_iKnowDatabase(application)

    // Gets all of the BroadcastActions
    fun getBroadcastActions(): LiveData<List<BroadcastAction>> {
        return db.iKnowDao().getAll()
    }

    // Gets BroadcastAction by ID
    fun getBroadcastActionById(id: Long): LiveData<BroadcastAction> {
        return db.iKnowDao().getById(id)
    }

    fun getBroadcastActionHashCoded(hashCode: Int?): BroadcastAction {
        return db.iKnowDao().getByHashcode(hashCode ?: 0)
    }

        // Gets all BroadcastActions by type
    fun getBroadcastActionsByType(type: String): LiveData<List<BroadcastAction>> {
        return db.iKnowDao().getByType(type)
    }


    // Adds one BroadcastAction
    fun addBroadcastAction(action: String, type: String, intentHashcode: Int) {
        val timestamp = ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())

        viewModelScope.launch(Dispatchers.IO) {
            db.iKnowDao().insert(
                BroadcastAction(
                    0,
                    action,
                    type,
                    true,
                    timestamp,
                    intentHashcode
                )
            )
        }
    }

    fun deleteBroadcastAction(broadcastAction: BroadcastAction) {
        viewModelScope.launch(Dispatchers.IO) {
            db.iKnowDao().delete(broadcastAction)
        }
    }

    fun changeBroadcastActionStatus(broadcastAction: BroadcastAction) {
        broadcastAction.status = !broadcastAction.status
        Log.d(TAG, broadcastAction.status.toString())

        viewModelScope.launch(Dispatchers.IO) {
            db.iKnowDao().update(broadcastAction)
        }
    }

    fun deleteAllBroadcastActions() {
        viewModelScope.launch(Dispatchers.IO) {
            db.iKnowDao().deleteAll()
        }
    }

}