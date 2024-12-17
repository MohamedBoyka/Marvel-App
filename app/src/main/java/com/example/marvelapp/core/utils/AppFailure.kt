package com.example.marvelapp.core.utils

abstract class AppFailure{
    abstract fun toErrorString(): String
}

class UnauthenticatedFailure: AppFailure(){
    override fun toErrorString(): String = "UnauthenticatedFailure"
}

class SomethingWentWrongFailure(private val message: String): AppFailure(){
    override fun toErrorString(): String = message
}