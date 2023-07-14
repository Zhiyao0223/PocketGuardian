package com.example.pocketguardian.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Homescreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val currentDrawerState = remember { mutableStateOf(drawerState) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "My App") })
        },
        drawerContent = {
            // Navigation menu items go here
            Column {
                Text(text = "Item 1")
                Text(text = "Item 2")
                Text(text = "Item 3")
            }
        },
        drawerGesturesEnabled = true,
        drawerElevation = DrawerDefaults.Elevation,
        drawerState = currentDrawerState.value,
        onDrawerStateChange = { newState ->
            currentDrawerState.value = newState
        }
    ) {
        Surface {
            // Your content goes here
            Column {
                Text(text = "Blank Screen")
            }
        }
    }
}
