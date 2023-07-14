package com.example.pocketguardian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pocketguardian.screens.Login
import com.example.pocketguardian.screens.RegisterPage
import com.example.pocketguardian.ui.theme.PocketGuardianTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PocketGuardianTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PocketGuardianApp()
                }
            }
        }
    }
}

@Composable
fun PocketGuardianApp() {
    PocketGuardianTheme {
        var isLoggedIn by remember { mutableStateOf(false) }

        // Check if the user is logged in
        if (!isLoggedIn) {
            Login(onLoginSuccess = { isLoggedIn = true })
        } else {
            var currentScreen: RallyDestination by remember { mutableStateOf(Overview) }

            Scaffold(
                topBar = {
                    RallyTabRow(
                        allScreens = rallyTabRowScreens,
                        onTabSelected = { screen -> currentScreen = screen },
                        currentScreen = currentScreen
                    )
                }
            ) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    currentScreen.screen()
                }
            }
        }
    }

//    val navController = rememberNavController() // Initialize the navController
//
//    NavHost(navController, startDestination = "login") {
//        composable("login") {
//            Login(navController)
//        }
//        composable("signup") {
//            RegisterPage(navController)
//        }
//    }
}