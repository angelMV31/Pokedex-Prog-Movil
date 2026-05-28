package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aerosj.myapplication.R
import com.aerosj.myapplication.data.model.PokemonDetails
import com.aerosj.myapplication.viewmodel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosScreen(navController: NavController, viewModel: PokemonViewModel) {
    val favoritos = viewModel.favoritosList
//Parte de arriba el topbar
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.pokedex_logo),
                            contentDescription = "Logo Pokedex",
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Pokedex", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        if (favoritos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes favoritos aún", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoritos) { pokemon ->
                    FavoritoCard(pokemon, viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun FavoritoCard(pokemon: PokemonDetails, viewModel: PokemonViewModel, navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate("detalle/${pokemon.nombre}")
            }
            .background(colorResource(R.color.pokedex_red))
            .padding(12.dp)
    ) {
        // Caja blanca para la imagen y la estrella
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            // Imagen del Pokemon centrada
            AsyncImage(
                model = pokemon.sprite.frente,
                contentDescription = pokemon.nombre,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(Alignment.Center)
            )

            // Estrella en la esquina superior derecha
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Quitar de favoritos",
                tint = Color.Black,
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-6).dp, y = 6.dp)
                    .clickable(onClick = {
                        viewModel.toggleFavorito(pokemon)
                    })
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Textos a la derecha
        Column {
            Text(
                text = "N# ${pokemon.id.toString().padStart(4, '0')}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp // Letra más grande, igual a tu imagen
            )
            Text(
                text = pokemon.nombre.replaceFirstChar { it.uppercase() },
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}