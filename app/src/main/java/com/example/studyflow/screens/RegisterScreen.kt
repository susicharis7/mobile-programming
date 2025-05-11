package com.example.studyflow.screens

// Foundation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

// Material3
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.HorizontalDivider

// Material icons (only once!)
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme

// Runtime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

// Resource
import com.example.studyflow.R
import com.example.studyflow.ui.theme.ButtonGradientColor
import com.example.studyflow.ui.theme.LoginGreen
import com.example.studyflow.ui.theme.TextBlack
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.theme.interFontFamily


@Composable
fun RegisterScreen(onLoginClick: () -> Unit) {
    var name by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var passwordVisible by remember {mutableStateOf(false)}

    val greenColor = Color(0xFF00E676)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // First Column - Modifying upper text
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 74.67.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "StudyFlow",
                style = MaterialTheme.typography.titleLarge,
                color = TextWhite
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(
                text = "Organise Your Future",
                color = Color(0xffE6E6E6),
                fontFamily = interFontFamily,
                fontSize = 25.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 0.05.em,
                modifier = Modifier
                    .fillMaxWidth()
                ,textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(46.dp)
            )

            Text(
                text = "Log Into Your Account",
                color = TextWhite,
                fontFamily = interFontFamily,
                fontSize = 26.67.sp,
                fontWeight = FontWeight.Bold
            )
        }


        // Second Column
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First box - Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = LoginGreen,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                TextField(
                    value = name,
                    onValueChange = {name = it},
                    label = {Text(
                                "Enter Your Name",
                                color = LoginGreen,
                                fontFamily = interFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                    )},
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = TextWhite)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = LoginGreen,
                        unfocusedLabelColor = LoginGreen,
                        cursorColor = LoginGreen
                    )
                )
            }

            // Second Box - Email
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .border(
                        width = 2.dp,
                        color = LoginGreen,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                TextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {Text("Enter your email", color = LoginGreen)},
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null, tint = TextWhite)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = LoginGreen,
                        unfocusedLabelColor = LoginGreen,
                        cursorColor = LoginGreen

                    )
                )
            }


            // Third Box - Password
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = LoginGreen,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                TextField(
                    value = password,
                    onValueChange = { password = it},
                    label = { Text(
                                "Enter Your Password",
                                color = LoginGreen,
                                fontFamily = interFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                        )},
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = TextWhite)
                    },
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Default.Visibility
                        else Icons.Default.VisibilityOff

                        val iconTint = if (passwordVisible) greenColor else LoginGreen

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = image,
                                contentDescription = null,
                                tint = iconTint
                            )

                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                                            else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = TextWhite,
                        unfocusedTextColor = TextWhite,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = LoginGreen,
                        unfocusedLabelColor = LoginGreen,
                        cursorColor = LoginGreen
                    )
                )


            }

            Spacer(modifier = Modifier.height(14.dp))

            // Button
            Button(
                onClick = {/* login */},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(
                        brush = Brush.horizontalGradient(ButtonGradientColor),
                        shape = RoundedCornerShape(2.dp)
                    ),
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "Create Account",
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    color = TextBlack
                )
            }

            Spacer(modifier = Modifier.height(13.67.dp))

            // Row
            Row {
                Text(
                    text = "Already have an account? ",
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = TextWhite
                )
                Text(
                    text = "Login",
                    color = LoginGreen,
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }







    }
}