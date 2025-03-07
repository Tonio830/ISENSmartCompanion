package fr.isen.campus.isensmartcompanion.compose

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import fr.isen.campus.isensmartcompanion.EventDetailActivity
import fr.isen.campus.isensmartcompanion.data.isenEvent
import fr.isen.campus.isensmartcompanion.retrofit.RetrofitInstance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    val context = LocalContext.current
    var events by remember { mutableStateOf<List<isenEvent>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitInstance.api.getEventList()  // Appel suspend
                events = response
                isLoading = false
            } catch (e: Exception) {
                errorMessage = "Erreur réseau : ${e.message}"
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Événements ISEN") })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !errorMessage.isNullOrEmpty() -> {
                    Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
                }
                !events.isNullOrEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(events!!) { event ->
                            EventButton(event = event) {
                                val intent = Intent(context, EventDetailActivity::class.java).apply {
                                    putExtra("event", event)
                                }
                                context.startActivity(intent)
                            }
                        }
                    }
                }
                else -> {
                    Text(text = "Aucun événement trouvé.", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun EventButton(event: isenEvent, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
    ) {
        Text(text = event.title)
    }
}


/*
val fakeEventsList = listOf(
    isenEvent(1, "BDE Evening", "Soirée organisée par le BDE", "15 Mars 2025", "Salle des fêtes", "Fête"),
    isenEvent(2, "Gala ISEN", "Gala annuel des étudiants", "10 Avril 2025", "Hôtel de ville", "Gala"),
    isenEvent(3, "Journée Cohésion", "Rencontre entre promos", "5 Septembre 2025", "Campus ISEN", "Rencontre")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen_Fake() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Événements ISEN") })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(fakeEventsList) { event ->
                    EventButton(event = event) {
                        val intent = Intent(context, EventDetailActivity::class.java).apply {
                            putExtra("event", event)
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}
*/