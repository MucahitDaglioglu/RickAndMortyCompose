package com.mucahitdaglioglu.rickandmorty.data.network

import com.mucahitdaglioglu.rickandmorty.data.model.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("api/character")
    suspend fun getAllCharacters(@Query("page") page: Int?) : Response<Characters>



}