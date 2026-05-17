package com.aerosj.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aerosj.myapplication.ui.screens.PokemonScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "pokemons"
    ) {
        composable("pokemons") {
            PokemonScreen(navController)
        }
    }
}