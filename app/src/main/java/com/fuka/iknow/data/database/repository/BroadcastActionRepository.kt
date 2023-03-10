package com.fuka.iknow.data.database.repository

import com.fuka.iknow.data.database.dao.BroadcastActionDao
import com.fuka.iknow.data.database.model.BroadcastAction
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject



/*
* Repo taso
* Kuten Peterin kanssa keskusteltu, emme nähneet järkeää tämän käyttöönotosta
* sillä kyseessä olisi aika pitkäli vain koodin duplikointia
* Teidostamme että edellä mainittu ei päde kaikkiin tilantaisiin ja että hyvin usein repon käyttäminen on järkevää
*
* */

class BroadcastActionRepository @Inject constructor(private val broadcastActionDao: BroadcastActionDao) {

    suspend fun addBroadcastAction(action: String, type: String) {

        val timestamp = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())

        val newActionData = BroadcastAction(0, action, type, true, timestamp, 4545)

        broadcastActionDao.insert(newActionData)
    }

    suspend fun updateBroadcastAction(broadcastAction: BroadcastAction)
            = broadcastActionDao.update(broadcastAction)

    suspend fun deleteBroadcastAction(broadcastAction: BroadcastAction)
            = broadcastActionDao.delete(broadcastAction)

    suspend fun getBroadcastActionById(id: Long)
            = broadcastActionDao.getById(id)

    suspend fun getBroadcastActionsByType(type: String)
        = broadcastActionDao.getByType(type)

    suspend fun getAllBroadcastActions()
        = broadcastActionDao.getAll()

    suspend fun deleteAllBroadcastActions()
        = broadcastActionDao.deleteAll()
}