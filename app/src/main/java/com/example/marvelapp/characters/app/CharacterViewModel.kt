package com.example.marvelapp.characters.app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelapp.characters.domain.entity.CharacterEntity
import com.example.marvelapp.characters.domain.usecase.GetCharacterUseCase
import com.example.marvelapp.core.utils.BaseViewModel
import com.example.marvelapp.core.utils.IMAGE_NOT_AVAILABLE
import com.example.marvelapp.core.utils.UnauthenticatedFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val useCase: GetCharacterUseCase
) : BaseViewModel() {

    private val _characters = MutableLiveData<List<CharacterEntity>>()
    val characters: LiveData<List<CharacterEntity>>
        get() = _characters

    init {
        getCharactersAPI()
    }

    private fun getCharactersAPI() {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                useCase.invoke().fold({ failure ->
                    when (failure) {
                        is UnauthenticatedFailure -> _unauthenticated.postValue(true)
                        else -> _error.postValue(failure.toErrorString())
                    }
                }, { characterList ->
                    val filteredList = characterList.filter { character ->
                        character.image != IMAGE_NOT_AVAILABLE && character.name.length < 20
                    }
                    _characters.value = filteredList
                    _loading.postValue(false)

                })
            } catch (e: Exception) {
                _error.postValue(e.message ?: "An unexpected error occurred")
            } finally {
                _loading.postValue(false)
            }
        }
    }
}

