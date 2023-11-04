package com.example.parcialtp3.di

import com.example.parcialtp3.data.repository.DogsRepositoryImpl
import com.example.parcialtp3.domain.repository.DogsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDogsRepository(dogsRepository: DogsRepositoryImpl): DogsRepository

}