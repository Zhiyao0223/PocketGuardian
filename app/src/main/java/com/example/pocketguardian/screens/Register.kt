package com.example.pocketguardian.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pocketguardian.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Register Page
@Composable
fun RegisterPage(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_playstore),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Register Page",
            style = MaterialTheme.typography.h6,
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = { Text(text = "Confirmed Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { performRegistration(context, email, password, confirmPass) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Register")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Already have an account? ",
                color = Color.Black
            )
            Text(
                text = "Login",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    // Handle navigation to the login page here
                    // For simplicity, this example displays a toast message
                    Toast.makeText(context, "Navigate to login page", Toast.LENGTH_SHORT).show()
                    navController.navigate("login")
                }
            )
        }
    }
}

// Function to perform registration
fun performRegistration(context: Context, email: String, password: String, confirmPass : String) {
    if (password != confirmPass) {
        Toast.makeText(context, "Registration failed: Different Password Found", Toast.LENGTH_SHORT).show()
        return
    }

    val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    val user = User(email, password)
    database.child("users").push().setValue(user)
        .addOnSuccessListener {
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "Registration failed: ${exception.message}", Toast.LENGTH_SHORT).show()
        }

    println("yeet")
}