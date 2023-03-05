package com.fuka.iknow.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fuka.iknow.data.database.model.BroadcastAction

@Dao
interface BroadcastActionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(broadcastAction: BroadcastAction)

    @Update
    suspend fun update(broadcastAction: BroadcastAction)

    @Delete
    suspend fun delete(broadcastAction: BroadcastAction)

    @Query("SELECT * FROM BroadcastAction WHERE id == :id")
    fun getById(id: Long): LiveData<BroadcastAction>

    @Query("SELECT * FROM BroadcastAction WHERE type == :type")
    fun getByType(type: String): LiveData<List<BroadcastAction>>

    @Query("SELECT * FROM BroadcastAction WHERE intentHashcode == :hashCode")
    fun getByHashcode(hashCode: Int): BroadcastAction

    @Query("SELECT * FROM BroadcastAction")
    fun getAll(): LiveData<List<BroadcastAction>>

    @Query("DELETE from BroadcastAction")
    fun deleteAll()
}