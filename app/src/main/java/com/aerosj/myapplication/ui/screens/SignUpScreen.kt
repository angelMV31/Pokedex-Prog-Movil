package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aerosj.myapplication.R
import com.aerosj.myapplication.ui.components.*
import com.aerosj.myapplication.viewmodel.AuthState
import com.aerosj.myapplication.viewmodel.AuthViewModel


@Composable
fun SignUpScreen(navController: NavHostController) {

    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmValue = remember { mutableStateOf("") }
    val viewModel: AuthViewModel = viewModel()
    val authValue by viewModel.authState.collectAsState()

    LaunchedEffect(authValue) {
        if (authValue is AuthState.Success) {
            navController.navigate("pokemons") {
                popUpTo("signup") { inclusive = true }
            }
            viewModel.resetState()
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
                painter = painterResource(id = R.drawable.pokedex_logo),
                contentDescription = stringResource(R.string.signIn_title),
                modifier = Modifier.size(100.dp)
                //colorFilter = ColorFilter.tint(colorResource(R.color.black))
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomInput(
                nameValue.value,
                stringResource(R.string.signUp_name_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { nameValue.value = it },
                "",
                false
            )

            CustomInput(
                emailValue.value,
                stringResource(R.string.signUp_email_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { emailValue.value = it },
                "",
                false
            )

            CustomInput(
                passwordValue.value,
                stringResource(R.string.signUp_pass_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { passwordValue.value = it },
                "",
                true
            )

            CustomInput(
                confirmValue.value,
                stringResource(R.string.signUp_confPass_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { confirmValue.value = it },
                "",
                true
            )

            if (authValue is AuthState.Error) {
                Text(
                    text = "Correo utilizado",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (authValue is AuthState.Loading) {
                CircularProgressIndicator(color = Color(0xFFE53935))
            } else {
                    CustomButton(
                        stringResource(R.string.signUp_button),
                        colorResource(R.color.white),
                        colorResource(R.color.pokedex_red)
                    ) {
                        if (passwordValue.value == confirmValue.value) {
                            viewModel.register(nameValue.value, emailValue.value, passwordValue.value)
                        }
                    }
                }

            Spacer(modifier = Modifier.height(20.dp))
            Row (

            ){
                BasicText(
                    text = stringResource(R.string.signUp_account_label),
                    style = TextStyle(
                        color = colorResource(R.color.input_text_color),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End
                    ),
                )

                Spacer(modifier = Modifier.width(10.dp))

                BasicText(
                    text = stringResource(R.string.signUp_title),
                    style = TextStyle(
                        color = colorResource(R.color.pokedex_red),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .clickable() {
                            navController.navigate("signin")
                        }
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false)
fun SignUpScreenPreview(){
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
