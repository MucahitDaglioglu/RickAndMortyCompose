package com.mucahitdaglioglu.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RickAndMortyDao {

    @Query("SELECT * FROM favorites_table")
    suspend fun allFavoriteCharacters(): List<CharacterDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCharacter(characterDetail: CharacterDetail)

    @Delete
    suspend fun deleteFavoriteCharacter(characterDetail: CharacterDetail)

}