package com.mucahitdaglioglu.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterDetail::class], version = 1, exportSchema = false)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun rickAndMortyDao(): RickAndMortyDao

}