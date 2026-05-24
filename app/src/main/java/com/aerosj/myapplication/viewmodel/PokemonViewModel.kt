package com.aerosj.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.aerosj.myapplication.data.model.PokemonDetails
import com.aerosj.myapplication.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private var allPokemons = listOf<PokemonDetails>()

    var pokemonList by mutableStateOf<List<PokemonDetails>>(emptyList())
        private set

    var searchQuery by mutableStateOf("")  //texto del buscador
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
                    repository.getPokemonDetails(result.nombre)
                }
                allPokemons = detallesLista
                pokemonList = detallesLista
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

    fun onSearchTextChange(query: String) {
        searchQuery = query
        pokemonList = if (query.isEmpty()) {
            allPokemons
        } else {
            allPokemons.filter { pokemon ->
                pokemon.nombre.startsWith(query, ignoreCase = true) ||
                        pokemon.id.toString() == query
            }
        }
    }
}