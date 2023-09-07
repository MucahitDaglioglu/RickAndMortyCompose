package com.mucahitdaglioglu.rickandmorty.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mucahitdaglioglu.rickandmorty.data.model.Result

class SharedViewModel : ViewModel() {

    var result by mutableStateOf<Result?>(null)
        private set

    fun addResult(newResult: Result?) {
        result = newResult
    }

}