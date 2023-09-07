package com.mucahitdaglioglu.rickandmorty.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mucahitdaglioglu.rickandmorty.data.paging.CharacterPagingSource
import com.mucahitdaglioglu.rickandmorty.data.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RickAndMortyRepository) : ViewModel() {

    val result = Pager(config = PagingConfig(20)) {
        CharacterPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}