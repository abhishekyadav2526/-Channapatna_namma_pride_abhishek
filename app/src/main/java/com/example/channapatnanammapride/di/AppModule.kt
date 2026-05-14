package com.example.channapatnanammapride.di

import com.example.channapatnanammapride.data.repository.ToyRepositoryImpl
import com.example.channapatnanammapride.domain.repository.ToyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindToyRepository(
        toyRepositoryImpl: ToyRepositoryImpl
    ): ToyRepository
}
