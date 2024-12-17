package com.example.marvelapp.core.di

import com.example.marvelapp.characters.data.datasources.CharacterDataSource
import com.example.marvelapp.characters.data.datasources.CharacterDataSourceImpl
import com.example.marvelapp.characters.data.repositories.CharacterRepositoryImpl
import com.example.marvelapp.characters.domain.repositories.CharacterRepository
import com.example.marvelapp.characters.domain.usecase.GetCharacterUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCharacterDataSource(
        dispatcher: CoroutineDispatcher
    ): CharacterDataSource {
        return CharacterDataSourceImpl(dispatcher)
    }

    @Provides
    fun provideCharacterRepository(
        characterDataSource: CharacterDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(characterDataSource)
    }

    @Provides
    fun provideGetCharacterUseCase(
        characterRepository: CharacterRepository
    ): GetCharacterUseCase {
        return GetCharacterUseCase(characterRepository)
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
