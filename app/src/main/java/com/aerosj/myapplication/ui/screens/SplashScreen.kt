package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aerosj.myapplication.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("signin") {
            popUpTo("splash") { inclusive = true } // evita volver al splash
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(colorResource(R.color.white)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(40.dp))
                .background(colorResource(R.color.white))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokedexlogo),
                contentDescription = stringResource(R.string.hihigh_logo),
                modifier = Modifier.size(250.dp)
                //colorFilter = ColorFilter.tint(colorResource(R.color.black))
            )

            Spacer(modifier = Modifier.height(40.dp))
            BasicText(
                text = stringResource(R.string.splash_text),
                style = TextStyle(
                    color = colorResource(R.color.black),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false)
fun SplashScreenPreview(){
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}

