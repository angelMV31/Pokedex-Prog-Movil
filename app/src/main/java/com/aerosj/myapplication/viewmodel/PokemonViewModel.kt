package com.aerosj.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.aerosj.myapplication.data.model.PokemonDetalles
import com.aerosj.myapplication.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    var pokemonList by mutableStateOf<List<PokemonDetalles>>(emptyList())
        private set

    private val repository = PokemonRepository()

    init {
        loadPokemons()
    }

    private fun loadPokemons(){
        viewModelScope.launch {
            try {
                val list = repository.getPokemonList()

                val detallesLista = list.pokemons.map { result ->
                    repository.getPokemonDetalle(result.nombre)
                }
                pokemonList = detallesLista
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
}