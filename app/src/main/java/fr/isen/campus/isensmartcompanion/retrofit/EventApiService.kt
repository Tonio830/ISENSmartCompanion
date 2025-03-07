package fr.isen.campus.isensmartcompanion.retrofit

import fr.isen.campus.isensmartcompanion.data.isenEvent
import retrofit2.http.GET

interface EventApiService {
    @GET("events.json")
    suspend fun getEventList(): List<isenEvent>
}

