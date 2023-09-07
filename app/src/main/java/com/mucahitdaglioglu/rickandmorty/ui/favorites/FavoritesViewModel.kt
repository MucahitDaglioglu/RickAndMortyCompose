package com.mucahitdaglioglu.rickandmorty.ui.favorites

import androidx.lifecycle.ViewModel
import com.mucahitdaglioglu.rickandmorty.data.local.CharacterDetail
import com.mucahitdaglioglu.rickandmorty.data.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: RickAndMortyRepository) : ViewModel() {

    val allFavorites: Flow<List<CharacterDetail>> = flow {
        emit(repository.allFavoriteCharacters())
    }.flowOn(Dispatchers.IO)

}