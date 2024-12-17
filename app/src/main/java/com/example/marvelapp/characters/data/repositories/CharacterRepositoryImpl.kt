package com.example.marvelapp.characters.data.repositories

import arrow.core.Either
import com.example.marvelapp.core.utils.AppFailure
import com.example.marvelapp.characters.data.datasources.CharacterDataSource
import com.example.marvelapp.characters.domain.entity.CharacterEntity
import com.example.marvelapp.characters.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val characterDataSource: CharacterDataSource):
    CharacterRepository {
    override suspend fun getCharacter(): Either<AppFailure, List<CharacterEntity>> =
        characterDataSource.getCharacter()

}