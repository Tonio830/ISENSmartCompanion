package fr.isen.campus.isensmartcompanion

import fr.isen.campus.isensmartcompanion.compose.isenEvent
import retrofit2.http.GET

interface EventApiService {
    @GET("events.json")
    suspend fun getEventList(): List<isenEvent>  // Utilise suspend ici
}

