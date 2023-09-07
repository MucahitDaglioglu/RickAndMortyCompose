package com.mucahitdaglioglu.rickandmorty.data.repository

import com.mucahitdaglioglu.rickandmorty.data.local.CharacterDetail
import com.mucahitdaglioglu.rickandmorty.data.model.Characters
import com.mucahitdaglioglu.rickandmorty.utils.Resource
import retrofit2.Response

interface RickAndMortyRepository {

    suspend fun getAllCharacters(page: Int?) : Resource<Characters>

    // db
    suspend fun allFavoriteCharacters() : List<CharacterDetail>

    suspend fun insertFavoriteCharacter(characterDetail: CharacterDetail)

    suspend fun deleteFavoriteCharacter(characterDetail: CharacterDetail)


}