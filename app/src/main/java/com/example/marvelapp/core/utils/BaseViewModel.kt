package com.example.marvelapp.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel(){

    private val viewModelJob = SupervisorJob()
    val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val _error = SingleLiveEvent<String?>()
    val error: LiveData<String?>
        get() = _error

    val _unauthenticated = MutableLiveData<Boolean>()
    val unauthenticated: LiveData<Boolean>
        get() = _unauthenticated



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}