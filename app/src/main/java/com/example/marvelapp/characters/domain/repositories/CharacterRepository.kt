package com.example.marvelapp.characters.domain.repositories

import arrow.core.Either
import com.example.marvelapp.core.utils.AppFailure
import com.example.marvelapp.characters.domain.entity.CharacterEntity

interface CharacterRepository {
    suspend fun getCharacter(): Either<AppFailure, List<CharacterEntity>>

}