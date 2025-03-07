package fr.isen.campus.isensmartcompanion.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import fr.isen.campus.isensmartcompanion.data.ChatDatabase
import fr.isen.campus.isensmartcompanion.data.ChatMessage
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("SimpleDateFormat")
@Composable
fun HistoryScreen() {
    val context = LocalContext.current
    val db = remember { ChatDatabase.getDatabase(context) }
    val chatDao = db.chatDao()
    val chatHistory = remember { mutableStateOf(emptyList<ChatMessage>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val messages = chatDao.getAllMessages()
            chatHistory.value = messages
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "History Screen", color = Color.Black)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(chatHistory.value) { message ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Question: ${message.question}")
                Text(text = "Réponse: ${message.answer}")
                Text(text = "Date: ${SimpleDateFormat("dd/MM/yyyy HH:mm").format(Date(message.date))}")

                IconButton(onClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        chatDao.deleteMessage(message)
                    }
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}
