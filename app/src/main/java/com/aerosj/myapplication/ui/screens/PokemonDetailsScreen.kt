package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.aerosj.myapplication.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aerosj.myapplication.ui.components.PokedexTopBar
import com.aerosj.myapplication.viewmodel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(
    navController: NavController,
    viewModel: PokemonViewModel,
    pokemonNombre: String?
) {
    // Buscamos el pokemon en la lista que ya cargó el ViewModel
    val pokemon = viewModel.pokemonList.find { it.nombre == pokemonNombre }

    Scaffold(
        topBar = {
            PokedexTopBar(onBackClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        if (pokemon != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen del Pokemon
                AsyncImage(
                    model = pokemon.sprite.frente,
                    contentDescription = pokemon.nombre,
                    modifier = Modifier.size(200.dp)
                )

                // Número
                Text(
                    text = "#${pokemon.id.toString().padStart(3, '0')}",
                    color = Color.Gray,
                    fontSize = 18.sp
                )

                // Nombre
                Text(
                    text = pokemon.nombre.uppercase(),
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tipos (Chips)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    pokemon.tipos?.forEach { tipoSlot ->
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(text = tipoSlot.type.name.replaceFirstChar { it.uppercase() })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Título Estadísticas
                Text(
                    text = "Estadisticas",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Barras de Estadísticas
                pokemon.estadisticas?.forEach { statSlot ->
                    StatRow(statName = statSlot.stat.name, statValue = statSlot.baseStat)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Título Descripción (Texto temporal, PokeAPI requiere otro endpoint para descripciones reales)
                Text(
                    text = "Descripción",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Un Pokémon extraño que tiene una semilla en el lomo. La semilla crece con él.",
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun StatRow(statName: String, statValue: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName.uppercase(),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.weight(0.25f)
        )
        Text(
            text = statValue.toString(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(0.15f)
        )

        // Barra de progreso
        val progress = statValue / 255f // 255 es el max stat base aproximado en pokemon
        val barColor = if (statValue > 100) Color.Green else if (statValue > 60) Color.Yellow else Color.Red

        Box(
            modifier = Modifier
                .weight(0.6f)
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.LightGray.copy(alpha = 0.5f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp))
                    .background(barColor)
            )
        }
    }
}