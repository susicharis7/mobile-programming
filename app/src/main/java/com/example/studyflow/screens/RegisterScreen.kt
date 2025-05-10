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

// Runtime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// UI
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Resource
import com.example.studyflow.R


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
                .padding(top = 65.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "StudyFlow",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Organise Your Future",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 27.sp,
                letterSpacing = 2.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 62.dp),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Text(
                text = "Create Your Account",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }


        // Second Column
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // First box - Name
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .border(
                        width = 2.dp,
                        color = greenColor,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                TextField(
                    value = name,
                    onValueChange = {name = it},
                    label = {Text("Enter Your Name", color = greenColor)},
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = greenColor,
                        unfocusedLabelColor = greenColor,
                        cursorColor = greenColor
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
                        color = greenColor,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                TextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {Text("Enter your email", color = greenColor)},
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null, tint = Color.White)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = greenColor,
                        unfocusedLabelColor = greenColor,
                        cursorColor = greenColor

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
                        color = greenColor,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                TextField(
                    value = password,
                    onValueChange = { password = it},
                    label = { Text("Enter Your Password", color = greenColor)},
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White)
                    },
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Default.Visibility
                        else Icons.Default.VisibilityOff

                        val iconTint = if (passwordVisible) greenColor else Color.Red

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
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = greenColor,
                        unfocusedLabelColor = greenColor,
                        cursorColor = greenColor
                    )
                )


            }

            Spacer(modifier = Modifier.height(14.dp))

            // Button
            Button(
                onClick = {/* login */},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenColor)
            ) {
                Text(
                    text = "Create Account",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    letterSpacing = 1.5.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Row
            Row {
                Text(
                    text = "Already have an account? ",
                    color = Color.White
                )
                Text(
                    text = "Login",
                    color = greenColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }







    }
}