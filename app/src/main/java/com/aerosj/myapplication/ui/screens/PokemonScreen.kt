package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.aerosj.myapplication.ui.components.Pokemon
import com.aerosj.myapplication.viewmodel.PokemonViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.aerosj.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(
    navHostController: NavHostController,
    viewModel: PokemonViewModel)
    {

    val listaPokemon = viewModel.pokemonList
    val searchQuery = viewModel.searchQuery
    var mostrarBuscador by remember { mutableStateOf(false) }  // controla si se ve el campo

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .padding(10.dp),

                title = {
                    if (mostrarBuscador){
                        // Campo de búsqueda
                        TextField(
                            value = searchQuery,
                            onValueChange = { viewModel.onSearchTextChange(it) },
                            placeholder = { Text("Nombre o número...") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    else {
                        Text("Pokedex", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    }
                },

                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.pokedex_logo),
                        contentDescription = "Pokedex",
                        modifier = Modifier
                            .size(32.dp)
                    )
                },

                actions = {
                    IconButton(onClick = {
                        if (mostrarBuscador)
                            mostrarBuscador = false
                        else
                            mostrarBuscador = true

                        if (!mostrarBuscador)
                            viewModel.onSearchTextChange("")
                    }) {
                        Icon(
                            imageVector = if (mostrarBuscador) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = true, onClick = { },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false, onClick = {navHostController.navigate("favoritos")},
                    icon = { Icon(Icons.Default.Star, contentDescription = "Favorite") },
                    label = { Text("Favorite") }
                )
                NavigationBarItem(
                    selected = false, onClick = {navHostController.navigate("usuario")},
                    icon = { Icon(Icons.Default.Person, contentDescription = "Account") },
                    label = { Text("Account") }
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.White)
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(18.dp)
                ) {
                    items(listaPokemon) { pokemon ->
                        Pokemon(pokemon = pokemon, viewModel = viewModel)
                    }
                }
            }
        }
    }
}