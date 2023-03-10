package com.fuka.iknow.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.fuka.iknow.data.database.dao.BroadcastActionDao
import com.fuka.iknow.data.database.dataBase.iKnowDatabase
import com.fuka.iknow.data.database.repository.BroadcastActionRepository
import com.fuka.iknow.viewModels.BroadcastActionViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Hiltin luokka
 * tässä luodaan instanssit joita halutaan injektoida hiltilla
 * yleisesti/normaalisti tässä luokassa luodaan instanssit niistä luokasita joiden luonti tapahtuu builder metodien kautta
 * Kuten opeille kerrottu, oli tuli outoja haasteita ja lopuksi päätettiin siirtämään jatkokehitysvaiheeseen.
 * */


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

    @Singleton
    @Provides
    fun provideBroadcastActionViewModel(braRepo: BroadcastActionRepository): BroadcastActionViewModel =
        BroadcastActionViewModel(braRepo)

}