package com.fuka.iknow.data.database.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fuka.iknow.data.database.converter.DateTypeConverter
import com.fuka.iknow.data.database.dao.BroadcastActionDao
import com.fuka.iknow.data.database.entity.BroadcastAction

@Database(entities = [BroadcastAction::class], version = 2, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class iKnowDatabase : RoomDatabase() {
    abstract fun broadcastActionDao(): BroadcastActionDao

    companion object {
        @Volatile
        private var INSTANCE: iKnowDatabase? = null

        fun get_iKnowDatabase(context: Context): iKnowDatabase? {
            synchronized(iKnowDatabase::class) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        iKnowDatabase::class.java, "iKnow_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}