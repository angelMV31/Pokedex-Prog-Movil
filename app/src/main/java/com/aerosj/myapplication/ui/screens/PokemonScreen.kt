package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aerosj.myapplication.ui.components.Pokemon
import com.aerosj.myapplication.viewmodel.PokemonViewModel

@Composable
fun PokemonScreen(viewModel: PokemonViewModel = viewModel()){

    val listaPokemon = viewModel.pokemonList

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffff0000))
    ){
        Text(
            text = "Pokedex Kanto",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .height(760.dp)
                .width(360.dp)
                .align(Alignment.BottomCenter)
        ) {
            LazyColumn(
            ) {
                items(listaPokemon){ pokemon ->
                    Pokemon(pokemon = pokemon)
                }
            }
        }
    }
}