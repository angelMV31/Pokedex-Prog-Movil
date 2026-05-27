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
    val sprite: PokemonSprite,
    @SerializedName("types")
    val tipos: List<PokemonTypeSlot>? = null,
    @SerializedName("stats")
    val estadisticas: List<PokemonStatSlot>? = null
)
data class PokemonSprite(
    @SerializedName("front_default")
    val frente: String
)

data class PokemonTypeSlot(
    @SerializedName("type")
    val type: PokemonType
)

data class PokemonType(
    @SerializedName("name")
    val name: String
)

data class PokemonStatSlot(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("stat")
    val stat: PokemonStatInfo
)

data class PokemonStatInfo(
    @SerializedName("name")
    val name: String
)