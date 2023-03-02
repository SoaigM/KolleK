package com.gyms.kollek.services

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "KollekResult")


@Serializable
data class KollekResultDto(
    val list: List<KollekMineralDTO>? = listOf()
)

@Serializable
data class KollekMineralDTO(
    val id: String,
    val name: String,
    val country: String,
    val image: String,
    val categoryId: String,
    val categoryName: String,
    val categoryColor: String,
) {

}
