package com.aerosj.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aerosj.myapplication.ui.screens.*
import com.aerosj.myapplication.viewmodel.PokemonViewModel

@Composable
fun AppNavigation(navController: NavHostController) {

    val pokemonViewModel: PokemonViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("pokemons") {
            PokemonScreen(navController, pokemonViewModel)
        }

        composable("signin") {
            SignInScreen(navController)
        }

        composable("signup") {
            SignUpScreen(navController)
        }

        composable("splash") {
            SplashScreen(navController)
        }

        composable("favoritos") {
            FavoritosScreen(navController, pokemonViewModel)
        }

        composable("usuario") {
            UsuarioScreen(navController)
        }
    }
}