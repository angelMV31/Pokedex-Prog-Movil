package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aerosj.myapplication.R
import com.aerosj.myapplication.ui.components.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignInScreen(navController: NavHostController) {

    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

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
                contentDescription = stringResource(R.string.signIn_title),
                modifier = Modifier.size(100.dp)
                //colorFilter = ColorFilter.tint(colorResource(R.color.black))
            )

            /*BasicText(
                text = stringResource(R.string.signIn_details),
                style = TextStyle(
                    color = colorResource(R.color.input_text_color),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),

            )*/

            Spacer(modifier = Modifier.height(30.dp))

            CustomInput(
                emailValue.value,
                stringResource(R.string.signIn_email_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { emailValue.value = it },
                "",
                false)

            CustomInput(
                passwordValue.value,
                stringResource(R.string.signUp_pass_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { passwordValue.value = it },
                "",
                true)

            CustomButton(
                stringResource(R.string.signIn_button),
                colorResource(R.color.white),
                colorResource(R.color.pokedex_red)
            ) {navController.navigate("pokemons")}

            Spacer(modifier = Modifier.height(20.dp))

            Row (
            ){
                BasicText(
                    text = stringResource(R.string.signIn_account_label),
                    style = TextStyle(
                        color = colorResource(R.color.input_text_color),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End
                    ),
                )

                Spacer(modifier = Modifier.width(10.dp))

                BasicText(
                    text = stringResource(R.string.signIn_title),
                    style = TextStyle(
                        color = colorResource(R.color.pokedex_red),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .clickable() {
                            navController.navigate("signup")
                        }
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = false)
fun SignInScreenPreview(){
    val navController = rememberNavController()
    SignInScreen(navController = navController)
}
