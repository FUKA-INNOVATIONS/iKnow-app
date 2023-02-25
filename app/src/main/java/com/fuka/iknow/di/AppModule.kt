package com.fuka.iknow.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.fuka.iknow.data.database.dao.BroadcastActionDao
import com.fuka.iknow.data.database.dataBase.iKnowDatabase
import com.fuka.iknow.data.database.repository.BroadcastActionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideBroadcastActionDao(database: iKnowDatabase): BroadcastActionDao = database.iKnowDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): iKnowDatabase =
        Room.databaseBuilder(context, iKnowDatabase::class.java, name = "iKnow_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideBroadcastActionRepository(): BroadcastActionRepository =
        BroadcastActionRepository(provideBroadcastActionDao(provideAppDatabase(Application())))

}