package com.aerosj.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aerosj.myapplication.ui.screens.*

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("pokemons") {
            PokemonScreen(navController)
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
    }
}