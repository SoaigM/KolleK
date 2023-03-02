package com.gyms.kollek.domain

import com.gyms.kollek.services.KollekMineralDTO
import java.time.LocalDateTime

data class KollekResultMinerals(
    val items: List<KollekMineral>? = listOf()
)

data class KollekMineral(
    val id: Int,
    val name: String,
    val country: String,
    val image: String,
    val categoryId: Int,
    val categoryName: String,
    val categoryColor: String,
)