package com.aerosj.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aerosj.myapplication.R

@Composable
fun CustomInput(
    value: String,
    label: String,
    borderColor: Color,
    textColor: Color,
    backgroundColor: Color,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    isPassword: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        BasicText(
            text = label,
            style = TextStyle(
                color = colorResource(R.color.black),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            visualTransformation = if (isPassword) PasswordVisualTransformation()
            else VisualTransformation.None,

            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(size = 8.dp))
                        .border(width = 2.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
                        .background(backgroundColor),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(color = Color.Black.copy(alpha = 0.6f), fontSize = 16.sp),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }

                    Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                        innerTextField()
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomInputPreview() {
    val state = remember { mutableStateOf("") }
    CustomInput(
        state.value,
        stringResource(R.string.signUp_pass_label),
        colorResource(R.color.airline_blue),
        colorResource(R.color.input_text_color),
        colorResource(R.color.white),
        { state.value = it },
        "",
        true)
}
