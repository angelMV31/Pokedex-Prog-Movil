package com.aerosj.myapplication.ui.components

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.aerosj.myapplication.R
import com.aerosj.myapplication.data.model.PokemonDetails
import com.aerosj.myapplication.viewmodel.PokemonViewModel

@Composable
fun Pokemon(pokemon: PokemonDetails, viewModel: PokemonViewModel, onClick: () -> Unit){

    val esCartaFavorita = viewModel.esFavorito(pokemon)
    //var esCartaFavorita by remember { mutableStateOf(false) }  // controla si la carta es favorita

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .width(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
            .background(colorResource(R.color.pokedex_red))
            .padding(10.dp)
    ) {
        // Área de imagen y estrella
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            // Estrella arriba a la derecha
            Icon(
                imageVector = if (esCartaFavorita) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = "Favorito",
                tint = Color.Black,
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-6).dp, y = 6.dp) // Colocar un poco hacia adentro
                    .clickable(onClick = {
                        viewModel.toggleFavorito(pokemon)
                    })
            )

            // Imagen centrada
            AsyncImage(
                model = pokemon.sprite.frente,
                contentDescription = pokemon.nombre,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(84.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Área de número y nombre centrada verticalmente
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            Text(
                text = "N.: ${pokemon.id.toString().padStart(4, '0')}",
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = pokemon.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/*@Composable
@Preview(showBackground = true)
fun PokemonPreview(){

    val pokemonPrueba = PokemonDetalles(
        nombre = "Charmander",
        sprite = PokemonSprite(
            frente = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
        )
    )
    Pokemon(pokemon = pokemonPrueba)
}*/