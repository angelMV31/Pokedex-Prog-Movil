package com.aerosj.myapplication.data.repository

import com.aerosj.myapplication.data.model.PokemonDetalles
import com.aerosj.myapplication.data.model.PokemonList
import com.aerosj.myapplication.data.remote.PokeApiInstance

class PokemonRepository {

    suspend fun getPokemonList(): PokemonList{
        return PokeApiInstance.api.get151Pokemon()
    }

    suspend fun getPokemonDetalle(name: String): PokemonDetalles{
        return PokeApiInstance.api.getPokemonDetalle(name)
    }
}