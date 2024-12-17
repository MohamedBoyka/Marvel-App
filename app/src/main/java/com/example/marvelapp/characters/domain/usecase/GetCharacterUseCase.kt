package com.example.marvelapp.characters.domain.usecase

import com.example.marvelapp.characters.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository){
    suspend operator fun  invoke() = characterRepository.getCharacter()

}