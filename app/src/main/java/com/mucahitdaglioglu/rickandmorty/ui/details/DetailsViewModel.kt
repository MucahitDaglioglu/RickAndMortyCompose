package com.mucahitdaglioglu.rickandmorty.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mucahitdaglioglu.rickandmorty.data.local.CharacterDetail
import com.mucahitdaglioglu.rickandmorty.data.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: RickAndMortyRepository) : ViewModel() {

    val allFavorites: Flow<List<CharacterDetail>> = flow {
        emit(repository.allFavoriteCharacters())
    }.flowOn(Dispatchers.IO)

    fun insertToFavorite(characterDetail: CharacterDetail) {
        viewModelScope.launch {
            repository.insertFavoriteCharacter(characterDetail)
        }
    }

    fun deleteFromFavorite(characterDetail: CharacterDetail) {
        viewModelScope.launch {
            repository.deleteFavoriteCharacter(characterDetail)
        }
    }

}