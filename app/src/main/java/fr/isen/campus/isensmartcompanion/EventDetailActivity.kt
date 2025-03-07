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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import fr.isen.campus.isensmartcompanion.data.isenEvent
import fr.isen.campus.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class EventDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val event = intent.getSerializableExtra("event") as? isenEvent

        setContent {
            ISENSmartCompanionTheme {
                if (event != null) {
                    EventDetailScreen(event) { finish() }
                } else {
                    Text(
                        "Erreur : événement introuvable",
                        color = Color.Red,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun EventDetailScreen(event: isenEvent, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black
                )

                Divider(color = Color.Gray, thickness = 1.dp)

                Text(
                    text = "📅 Date : ${event.date}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )

                Text(
                    text = "📍 Lieu : ${event.location}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )

                Text(
                    text = "🏷️ Catégorie : ${event.category}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )

                Text(
                    text = "ℹ️ Détails : ${event.description}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Retour", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

