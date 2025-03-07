package fr.isen.campus.isensmartcompanion.data


import androidx.room.*

@Dao
interface ChatDao {

    @Insert
    suspend fun insertMessage(chatMessage: ChatMessage)

    @Delete
    suspend fun deleteMessage(chatMessage: ChatMessage)

    @Query("DELETE FROM chat_history")
    suspend fun deleteAllMessages()

    @Query("SELECT * FROM chat_history ORDER BY date DESC")
    fun getAllMessages(): List<ChatMessage>
}
