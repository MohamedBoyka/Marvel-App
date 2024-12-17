package com.example.marvelapp.characters.data.datasources

import arrow.core.Either
import com.example.marvelapp.core.network.ApiManager
import com.example.marvelapp.core.utils.AppFailure
import com.example.marvelapp.core.utils.SUCCESS
import com.example.marvelapp.core.utils.SomethingWentWrongFailure
import com.example.marvelapp.core.utils.USER_UNAUTHENTICATED
import com.example.marvelapp.core.utils.UnauthenticatedFailure
import com.example.marvelapp.characters.data.model.togetCharacterDomain
import com.example.marvelapp.characters.domain.entity.CharacterEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) : CharacterDataSource {

    override suspend fun getCharacter(): Either<AppFailure, List<CharacterEntity>> =
        withContext(dispatcher) {
            try {
                val response = ApiManager.apiCalls.getCharacter(
                    timestamp = "1734379147",
                    apiKey = "20b8818d63902a8e9e2d320e65305af9",
                    hash = "dd8cf9178d22f472b005c9bb1608e7a2",
                    offset = 0,
                    limit = 20
                )

                when (response.code()) {
                    SUCCESS -> {
                        response.body()?.let { body ->
                            return@withContext Either.Right(body.data.results.togetCharacterDomain())
                        } ?: return@withContext Either.Left(SomethingWentWrongFailure("Response body is null"))
                    }

                    USER_UNAUTHENTICATED -> {
                        return@withContext Either.Left(UnauthenticatedFailure())
                    }

                    else -> {
                        val errorMessage = response.errorBody()?.string()?.let {
                            JSONObject(it).optString("message", "Unknown error occurred")
                        } ?: "Unknown error occurred"
                        return@withContext Either.Left(SomethingWentWrongFailure(errorMessage))
                    }
                }
            } catch (e: Exception) {
                return@withContext Either.Left(
                    SomethingWentWrongFailure(e.message ?: "An unexpected error occurred")
                )
            }
        }
}

