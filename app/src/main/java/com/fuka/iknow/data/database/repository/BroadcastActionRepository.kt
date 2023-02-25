package com.fuka.iknow.data.database.repository

import com.fuka.iknow.data.database.dao.BroadcastActionDao
import com.fuka.iknow.data.database.model.BroadcastAction
import javax.inject.Inject

class BroadcastActionRepository @Inject constructor(private val broadcastActionDao: BroadcastActionDao) {

    suspend fun addBroadcastAction(broadcastAction: BroadcastAction)
        = broadcastActionDao.insertBroadcastAction(broadcastAction)

    suspend fun updateBroadcastAction(broadcastAction: BroadcastAction)
            = broadcastActionDao.updateBroadcastAction(broadcastAction)

    suspend fun deleteBroadcastAction(broadcastAction: BroadcastAction)
            = broadcastActionDao.deleteBroadcastAction(broadcastAction)

    suspend fun getBroadcastActionById(id: Long)
            = broadcastActionDao.getBroadcastActionById(id)

    suspend fun getBroadcastActionsByType(type: String)
        = broadcastActionDao.getBroadcastActionsByType(type)

    suspend fun getAllBroadcastActions()
        = broadcastActionDao.getBroadcastActions()


}