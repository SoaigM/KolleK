package com.gyms.kollek.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

class KollekAPI(
    private val client: HttpClient,
    var baseUrl: String = "http://10.0.2.2:5000",
) : KoinComponent {

    suspend fun fetchMinerals() = client.get("$baseUrl/mineral"){
        url{

        }
    }.body<KollekResultDto>()
}
