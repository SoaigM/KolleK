package com.gyms.kollek.services

import com.gyms.kollek.components.Screen
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent

class KollekAPI(
    private val client: HttpClient,
    var baseUrl: String = "http://10.0.2.2:5000",
) : KoinComponent {

    suspend fun fetchMineralsList() = client.get("$baseUrl/mineral"){
        url{

        }
    }.body<KollekMineralListDto>()

    suspend fun fetchMineralDetail(id: Int) = client.get("$baseUrl/mineral/$id"){
        url{

        }
    }.body<KollekMineralDetailDTO>()

    suspend fun fetchCategoryList() = client.get("$baseUrl/category"){
        url{

        }
    }.body<KollekCategoryListDTO>()

    suspend fun fetchCategoryDetail(id: Int) = client.get("$baseUrl/category/$id"){
        url{

        }
    }.body<KollekCategoryDetailListDto>()
}
