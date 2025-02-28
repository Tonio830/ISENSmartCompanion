package fr.isen.campus.isensmartcompanion.compose

import java.io.Serializable

data class isenEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val category: String
) : Serializable
