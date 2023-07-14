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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pocketguardian.R
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*


data class User(val email: String, val password: String)

@Composable
fun Login(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
            text = "Login",
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
        Button(
            onClick = { performLogin(context, email, password) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Don't have an account? ",
                color = Color.Black
            )
            Text(
                text = "Sign up",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    // Handle navigation to the register page here
                    Toast.makeText(context, "Navigate to sign up page...", Toast.LENGTH_SHORT).show()
                    navController.navigate("signup")
                }
            )
        }
    }
}


// Example login function
fun performLogin(context: Context, email: String, password: String) {
    FirebaseApp.initializeApp(context)
    val database = FirebaseDatabase.getInstance()
    val reference: DatabaseReference = database.getReference("users")

    val query: Query = reference.orderByChild("email").equalTo(email)

    query.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            var isLoginSuccessful = false

            for (userSnapshot in dataSnapshot.children) {
                val user = userSnapshot.getValue(User::class.java)

                if (user != null && user.password == password) {
                    // Login successful
                    isLoginSuccessful = true
                    break
                }
            }

            if (isLoginSuccessful) {
                // Perform login success action
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                println("Login")
            } else {
                // Perform login failure action
                Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                println("Fail")
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle the database error
            Toast.makeText(context, "Database error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
        }
    })
}

