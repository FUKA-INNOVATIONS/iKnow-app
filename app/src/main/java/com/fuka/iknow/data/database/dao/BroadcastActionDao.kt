package com.fuka.iknow.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fuka.iknow.data.database.model.BroadcastAction

@Dao
interface BroadcastActionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBroadcastAction(broadcastAction: BroadcastAction)

    @Update
    suspend fun updateBroadcastAction(broadcastAction: BroadcastAction)

    @Delete
    suspend fun deleteBroadcastAction(broadcastAction: BroadcastAction)

    @Query("SELECT * FROM BroadcastAction WHERE id == :id")
    fun getBroadcastActionById(id: Long): LiveData<BroadcastAction>

    @Query("SELECT * FROM BroadcastAction WHERE type == :type")
    fun getBroadcastActionsByType(type: String): LiveData<List<BroadcastAction>>

    @Query("SELECT * FROM BroadcastAction")
    fun getBroadcastActions(): LiveData<List<BroadcastAction>>

    @Query("DELETE from BroadcastAction")
    fun deleteAllBroadcastActions()
}