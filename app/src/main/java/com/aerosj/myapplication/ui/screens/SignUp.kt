package com.aerosj.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aerosj.myapplication.R
import com.aerosj.myapplication.ui.components.*


@Composable
fun SignUpScreen(navController: NavHostController) {

    val state = remember { mutableStateOf("") }

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
            Spacer(modifier = Modifier.height(20.dp))

            CustomInput(
                state.value,
                stringResource(R.string.signUp_name_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { state.value = it },
                "",
                false)

            CustomInput(
                state.value,
                stringResource(R.string.signUp_email_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { state.value = it },
                "",
                false)

            CustomInput(
                state.value,
                stringResource(R.string.signUp_pass_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { state.value = it },
                "",
                true)

            CustomInput(
                state.value,
                stringResource(R.string.signUp_confPass_label),
                colorResource(R.color.input_text_color),
                colorResource(R.color.input_text_color),
                colorResource(R.color.white),
                { state.value = it },
                "",
                true)

            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                stringResource(R.string.signUp_button),
                colorResource(R.color.white),
                colorResource(R.color.pokedex_red)
            ) { }

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
                )
            }
        }
    }
}


/*@Composable
@Preview(showBackground = true, showSystemUi = false)
fun SignUpScreenPreview(){
    SignUpScreen()
}*/

annotation class SignUp
