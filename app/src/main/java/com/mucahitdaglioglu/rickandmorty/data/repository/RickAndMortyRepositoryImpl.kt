package com.mucahitdaglioglu.rickandmorty.data.repository

import com.mucahitdaglioglu.rickandmorty.data.local.CharacterDetail
import com.mucahitdaglioglu.rickandmorty.data.local.RickAndMortyDao
import com.mucahitdaglioglu.rickandmorty.data.model.Characters
import com.mucahitdaglioglu.rickandmorty.data.network.RickAndMortyService
import retrofit2.Response
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyService,
    private val rickAndMortyDao: RickAndMortyDao ) : RickAndMortyRepository {

    override suspend fun getAllCharacters(page: Int?): Response<Characters> {
        return apiService.getAllCharacters(page)
    }

    override suspend fun allFavoriteCharacters(): List<CharacterDetail> {
        return rickAndMortyDao.allFavoriteCharacters()
    }

    override suspend fun insertFavoriteCharacter(characterDetail: CharacterDetail) {
        return rickAndMortyDao.insertFavoriteCharacter(characterDetail)
    }

    override suspend fun deleteFavoriteCharacter(characterDetail: CharacterDetail) {
        return rickAndMortyDao.deleteFavoriteCharacter(characterDetail)
    }

}