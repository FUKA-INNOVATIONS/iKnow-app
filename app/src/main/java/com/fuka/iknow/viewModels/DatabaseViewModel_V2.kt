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

val TAGG = "iKnow-app"

class DatabaseViewModel_V2 () : ViewModel() {


    private val db = iKnowDatabase.get_iKnowDatabase(Application())

    private lateinit var _broadcastActionList: LiveData<List<BroadcastAction>>
    var broadcastActionList = _broadcastActionList

    private lateinit var _singleBroadcastAction: MutableLiveData<LiveData<BroadcastAction>>

    private lateinit var _allBroadcastActionsByType: MutableLiveData<LiveData<List<BroadcastAction>>>

    init {
        getBroadcastActions()
    }

    // Gets all of the BroadcastActions
    private fun getBroadcastActions() {
        //viewModelScope.launch {  all.value = repository.getAllBroadcastActions() }
        viewModelScope.launch {  _broadcastActionList = db.iKnowDao().getAll() }
    }

    // Gets BroadcastAction by ID
    fun getBroadcastActionById(id: Long) {
        //viewModelScope.launch { single.value = repository.getBroadcastActionById(id) }
        viewModelScope.launch { _singleBroadcastAction.value = db.iKnowDao().getById(id) }
    }

    // Gets all BroadcastActions by type
    fun getBroadcastActionsByType(type: String) {
        //viewModelScope.launch { allByType.value = repository.getBroadcastActionsByType(type) }
        viewModelScope.launch { _allBroadcastActionsByType.value = db.iKnowDao().getByType(type) }
    }


    // Adds one BroadcastAction
    fun addBroadcastAction(action: String, type: String) {

        //viewModelScope.launch(Dispatchers.IO) { repository.addBroadcastAction(action, type) }

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
                    timestamp
                )
            )
        }
    }

    fun deleteBroadcastAction(broadcastAction: BroadcastAction) {
        viewModelScope.launch(Dispatchers.IO) {
            //repository.deleteBroadcastAction(broadcastAction)
            db.iKnowDao().delete(broadcastAction)
        }
    }

    fun changeBroadcastActionStatus(broadcastAction: BroadcastAction) {
        broadcastAction.status = !broadcastAction.status
        Log.d(TAG, broadcastAction.status.toString())

        viewModelScope.launch(Dispatchers.IO) {
            //repository.updateBroadcastAction(broadcastAction)
            db.iKnowDao().update(broadcastAction)
        }
    }

}