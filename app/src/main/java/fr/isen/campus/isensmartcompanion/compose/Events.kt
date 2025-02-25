package fr.isen.campus.isensmartcompanion.compose

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.isen.campus.isensmartcompanion.EventDetailActivity

// Liste d'événements fictifs
val fakeEventsList = listOf(
    isenEvent(1, "BDE Evening", "Soirée organisée par le BDE", "15 Mars 2025", "Salle des fêtes", "Fête"),
    isenEvent(2, "Gala ISEN", "Gala annuel des étudiants", "10 Avril 2025", "Hôtel de ville", "Gala"),
    isenEvent(3, "Journée Cohésion", "Rencontre entre promos", "5 Septembre 2025", "Campus ISEN", "Rencontre")
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
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
                            putExtra("event", event) // On passe l'objet isenEvent
                        }
                        context.startActivity(intent)
                    }
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
            .padding(16.dp) // Agrandir l'espace autour du bouton
            .height(60.dp), // Ajuster la hauteur du bouton
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray) // Changer la couleur en gris
    ) {
        Text(text = event.title)
    }
}

