package fr.isen.campus.isensmartcompanion.compose

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import fr.isen.campus.isensmartcompanion.R
import fr.isen.campus.isensmartcompanion.data.ChatDatabase
import fr.isen.campus.isensmartcompanion.data.ChatMessage
import fr.isen.campus.isensmartcompanion.retrofit.GeminiApiService
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    var text by remember { mutableStateOf("") }
    var displayedText by remember { mutableStateOf("") }
    var responseText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val db = remember { ChatDatabase.getDatabase(context) }
    val chatDao = remember { db.chatDao() }

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
                label = { Text("Votre question") }
            )
            Button(
                onClick = {
                    displayedText = "YOU: $text"
                    responseText = ""
                    isLoading = true

                    coroutineScope.launch {
                        try {

                            val aiResponse = GeminiApiService.getAiResponse(text)
                            isLoading = false
                            responseText = aiResponse

                            val chatMessage = ChatMessage(question = text, answer = aiResponse)
                            withContext(Dispatchers.IO) {
                                chatDao.insertMessage(chatMessage)
                            }
                        } catch (e: Exception) {
                            isLoading = false
                            responseText = "Erreur : ${e.message}"
                        }
                    }
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

        if (isLoading) {
            Spacer(modifier = Modifier.height(10.dp))
            CircularProgressIndicator()
        } else if (responseText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = responseText, color = Color.Black)
        }
    }
}
