package com.aerosj.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aerosj.myapplication.ui.screens.PokemonScreen
import com.aerosj.myapplication.ui.screens.SignIn
import com.aerosj.myapplication.ui.screens.SignInScreen
import com.aerosj.myapplication.ui.screens.SignUp
import com.aerosj.myapplication.ui.screens.SignUpScreen
import com.aerosj.myapplication.ui.screens.Splash
import com.aerosj.myapplication.ui.screens.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "pokemons"
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