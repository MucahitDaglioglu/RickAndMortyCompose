package com.mucahitdaglioglu.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.mucahitdaglioglu.rickandmorty.data.local.RickAndMortyDao
import com.mucahitdaglioglu.rickandmorty.data.local.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDao(rickAndMortyDatabase: RickAndMortyDatabase): RickAndMortyDao {
        return rickAndMortyDatabase.rickAndMortyDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): RickAndMortyDatabase {
        return Room.databaseBuilder(appContext, RickAndMortyDatabase::class.java, "rickandmorty.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}