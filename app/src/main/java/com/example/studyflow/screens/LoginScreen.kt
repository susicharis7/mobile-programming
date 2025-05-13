package com.example.studyflow.screens

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyflow.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import com.example.studyflow.ui.theme.*


@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val context = LocalContext.current

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

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Organise Your Future",
                color = Color(0xffE6E6E6),
                fontFamily = interFontFamily,
                fontSize = 25.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 0.05.em,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(46.dp))

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
            // First Box - Username/Email
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, if (emailError) RedValidationColor else LoginGreen, RoundedCornerShape(4.dp))
                ) {
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = false
                        },
                        label = {
                            Text(
                                text = if (emailError) "Please enter a valid email" else "Email",
                                color = if (emailError) RedValidationColor else LoginGreen,
                                fontFamily = interFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                            )
                        },
                        isError = emailError,
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null, tint = TextWhite)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            errorTextColor = TextWhite,
                            errorLabelColor = RedValidationColor,
                            errorLeadingIconColor = TextWhite,
                            errorTrailingIconColor = LoginGreen,
                            focusedLabelColor = LoginGreen,
                            unfocusedLabelColor = LoginGreen,
                            cursorColor = LoginGreen
                        )
                    )
                }
            }

            // Second Box - Password
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .border(2.dp, if (passwordError) RedValidationColor else LoginGreen, RoundedCornerShape(4.dp))
                ) {
                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = false
                        },
                        label = {
                            Text(
                                text = if (passwordError) "Password must be at least 8 characters long and include a number" else "Password",
                                color = if (passwordError) RedValidationColor else LoginGreen,
                                fontFamily = interFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                            )
                        },
                        isError = passwordError,
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = TextWhite)
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            errorTextColor = TextWhite,
                            errorLabelColor = RedValidationColor,
                            errorLeadingIconColor = TextWhite,
                            errorTrailingIconColor = LoginGreen,
                            focusedLabelColor = LoginGreen,
                            unfocusedLabelColor = LoginGreen,
                            cursorColor = LoginGreen
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Button
            Button(
                onClick = {
                    emailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    passwordError = !(password.length >= 8 && password.any { it.isDigit() })

                    if (!emailError && !passwordError) {
                        Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(
                        brush = Brush.horizontalGradient(ButtonGradientColor),
                        shape = RoundedCornerShape(2.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(2.dp)
            ) {
                Text(
                    text = "Login",
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
                    text = "Do not have an account? ",
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = TextWhite
                )
                Text(
                    text = "Register Now",
                    color = LoginGreen,
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
    }
}
