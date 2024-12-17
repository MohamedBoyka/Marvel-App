package com.example.marvelapp.core.network

import com.example.marvelapp.characters.data.model.MarvelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APICalls {

    @GET("characters")
    suspend fun getCharacter(
        @Query("ts") timestamp: String /*= "1734379147" */,
        @Query("apikey") apiKey: String /*= "20b8818d63902a8e9e2d320e65305af9"*/,
        @Query("hash") hash: String /*= "dd8cf9178d22f472b005c9bb1608e7a2"*/,
        @Query("offset") offset: Int /*= 1*/,
        @Query("limit") limit: Int/* = 20*/
    ): Response<MarvelResponse>
}
