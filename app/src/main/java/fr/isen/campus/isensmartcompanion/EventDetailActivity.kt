package fr.isen.campus.isensmartcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import fr.isen.campus.isensmartcompanion.compose.isenEvent
import fr.isen.campus.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class EventDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Récupérer l'objet isenEvent
        val event = intent.getSerializableExtra("event") as? isenEvent

        setContent {
            ISENSmartCompanionTheme {
                if (event != null) {
                    EventDetailScreen(event) { finish() } // Passer la fonction pour retourner
                } else {
                    Text("Erreur : événement introuvable")
                }
            }
        }
    }
}

@Composable
fun EventDetailScreen(event: isenEvent, onBackClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Détails de l'événement", color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Titre : ${event.title}", color = Color.Black)
            Text(text = "Date : ${event.date}", color = Color.Black)
            Text(text = "Lieu : ${event.location}", color = Color.Black)
            Text(text = "Catégorie : ${event.category}", color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = onBackClick, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text("Retour")
            }
        }
    }
}
