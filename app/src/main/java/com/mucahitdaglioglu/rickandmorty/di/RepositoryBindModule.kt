package com.mucahitdaglioglu.rickandmorty.di

import com.mucahitdaglioglu.rickandmorty.data.repository.RickAndMortyRepository
import com.mucahitdaglioglu.rickandmorty.data.repository.RickAndMortyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryBindModule() {

    @Binds
    abstract fun bindRepository(repositoryImpl: RickAndMortyRepositoryImpl) : RickAndMortyRepository
}