package com.aerosj.myapplication.data.remote

import com.aerosj.myapplication.data.model.PokemonList
import com.aerosj.myapplication.data.model.PokemonDetalles
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=151")
    suspend fun get151Pokemon(): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonDetalle(@Path("name") name: String): PokemonDetalles
}