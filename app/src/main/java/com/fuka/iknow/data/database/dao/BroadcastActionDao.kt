package com.fuka.iknow.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fuka.iknow.data.database.entity.BroadcastAction

@Dao
interface BroadcastActionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateBroadcastAction(broadcastAction: BroadcastAction)

    @Update
    suspend fun updateBroadcastAction(broadcastAction: BroadcastAction)

    @Delete
    suspend fun deleteBroadcastAction(broadcastAction: BroadcastAction)

    @Query("SELECT * FROM BroadcastAction WHERE id == :id")
    fun getBroadcastActionByID(id: Long): LiveData<BroadcastAction>

    @Query("SELECT * FROM BroadcastAction WHERE type == :type")
    fun getBroadcastActionsByType(type: String): LiveData<List<BroadcastAction>>

    @Query("SELECT * FROM BroadcastAction")
    fun getBroadcastActions(): LiveData<List<BroadcastAction>>
}