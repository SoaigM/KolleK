package com.gyms.kollek.services

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity(tableName = "KollekResult")


@Serializable
data class KollekResultDto(
    val result: List<KollekMineralDTO>? = listOf()
)

@Serializable
data class KollekMineralDTO(
    val id: Int,
    val name: String,
    val country: String,
    val image: String,
    val categoryId: Int,
    val categoryName: String,
    val categoryColor: String,
) {

}
