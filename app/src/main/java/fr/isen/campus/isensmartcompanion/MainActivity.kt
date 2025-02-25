package fr.isen.campus.isensmartcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
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

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    var text by remember { mutableStateOf("") }
    var displayedText by remember { mutableStateOf("") }
    var responseText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoisen),
            contentDescription = "Logo ISEN",
            modifier = Modifier.size(100.dp)
        )
        Text(text = "Hello ISEN")
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),

            )
            Button(
                onClick = {
                    displayedText = "YOU: $text"
                    responseText = "REPONSE: Je ne comprends pas"
                },
                modifier = Modifier.padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Send Arrow"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = displayedText, color = Color.Black)
        if (responseText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = responseText, color = Color.Gray)
        }
    }
}

@Composable
fun EventsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Events Screen", color = Color.Black)
    }
}

@Composable
fun HistoryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "History Screen", color = Color.Black)
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