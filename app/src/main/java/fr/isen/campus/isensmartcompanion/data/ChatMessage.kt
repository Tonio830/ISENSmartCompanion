package fr.isen.campus.isensmartcompanion.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "chat_history")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val question: String,
    val answer: String,
    val date: Long = System.currentTimeMillis() // Timestamp de la date de l'échange
)
