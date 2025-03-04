package fr.isen.campus.isensmartcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.*
import fr.isen.campus.isensmartcompanion.compose.MainScreen
import fr.isen.campus.isensmartcompanion.compose.EventsScreen
import fr.isen.campus.isensmartcompanion.compose.HistoryScreen
import fr.isen.campus.isensmartcompanion.ui.theme.ISENSmartCompanionTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ISENSmartCompanionTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { NavigationBar(navController) }
    ) { innerPadding ->
        NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {
            composable("home") { MainScreen(innerPadding) }
            composable("events") { EventsScreen() }
            composable("history") { HistoryScreen() }
        }
    }
}

@Composable
fun NavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = { Icon(painterResource(id = R.drawable.house), contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("events") },
            icon = { Icon(painterResource(id = R.drawable.event), contentDescription = "Events") },
            label = { Text("Events") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("history") },
            icon = { Icon(painterResource(id = R.drawable.book), contentDescription = "History") },
            label = { Text("History") }
        )
    }
}



/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ISENSmartCompanionTheme {
        Greeting("Android")
    }
}
*/