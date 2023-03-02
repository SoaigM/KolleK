package com.gyms.kollek.domain

import com.gyms.kollek.services.KollekMineralDTO
import java.time.LocalDateTime

data class KollekResultMinerals(
    val items: List<KollekMineral>? = listOf()
)

data class KollekMineral(
    val id: String,
    val name: String,
    val country: String,
    val image: String,
    val categoryId: String,
    val categoryName: String,
    val categoryColor: String,
)