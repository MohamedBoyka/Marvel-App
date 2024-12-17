package com.example.marvelapp.characters.data.datasources

import arrow.core.Either
import com.example.marvelapp.core.utils.AppFailure
import com.example.marvelapp.characters.domain.entity.CharacterEntity

interface CharacterDataSource {
    suspend fun getCharacter(): Either<AppFailure, List<CharacterEntity>>

}