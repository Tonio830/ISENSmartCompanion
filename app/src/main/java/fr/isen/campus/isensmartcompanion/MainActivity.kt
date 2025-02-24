package fr.isen.campus.isensmartcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource // ✅ Ajouté pour charger l'image
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.isen.campus.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ISENSmartCompanionTheme {
                Scaffold { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}
/*
@Composable
fun MainScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ){
        Images(Painter = painterResource(R.drawable.logoisen),
        Text(
            text = "Hello ISEN"
        )
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.Red)
        ){
            TextField(
                value = "test",
                onValuesChange = {}
            )
            //Image(painter = painterResources(R.drawable))
        }
    }
} */

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally //Ajouté pour centrer le contenu
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoisen), // Chargement de l'image depuis drawable
            contentDescription = "Logo ISEN",
            modifier = Modifier.size(100.dp) // Taille de l'image
        )
        Text(
            text = "Hello ISEN"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            verticalAlignment = Alignment.CenterVertically // Alignement vertical pour la Row
        ) {
            TextField(
                value = "", // Ajout d'une valeur vide pour éviter l'erreur
                onValueChange = {}, // Ajout de la fonction pour éviter l'erreur
                modifier = Modifier.weight(1f) // Permet au champ de texte d'occuper l'espace disponible
            )
            Image(
                painter = painterResource(id = R.drawable.arrow), // Chargement d'une autre image
                contentDescription = "Icône d'envoi",
                modifier = Modifier.size(50.dp) // Taille de l'image
            )
        }
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