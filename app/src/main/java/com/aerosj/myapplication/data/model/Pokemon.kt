package com.aerosj.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("results")
    val pokemons: List<PokemonAnswer>
)

data class PokemonAnswer(
    @SerializedName("name")
    val nombre: String,
    val url: String
)

data class PokemonDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("sprites")
    val sprite: PokemonSprite
)

data class PokemonSprite(
    @SerializedName("front_default")
    val frente: String
)