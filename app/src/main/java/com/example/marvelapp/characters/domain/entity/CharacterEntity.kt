package com.example.marvelapp.characters.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterEntity(
    val id: Int,
    val name: String,
    val image: String,
): Parcelable