package com.aerosj.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aerosj.myapplication.data.model.PokemonDetalles
import com.aerosj.myapplication.data.model.PokemonSprite

@Composable
fun Pokemon(pokemon: PokemonDetalles){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xfff5f5f5))
            .height(50.dp)
    ) {
        AsyncImage(
            model = pokemon.sprite.frente,
            contentDescription = pokemon.nombre,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = pokemon.nombre,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PokemonPreview(){

    val pokemonPrueba = PokemonDetalles(
        nombre = "Charmander",
        sprite = PokemonSprite(
            frente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
        )
    )
    Pokemon(pokemon = pokemonPrueba)
}