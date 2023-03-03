package com.gyms.kollek.services

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity(tableName = "KollekResult")


@Serializable
data class KollekMineralListDto(
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
)


@Serializable
data class KollekMineralDetailDTO(
    val category: Int,
    val country: String,
    val id: Int,
    val image: String,
    val name: String,
)
@Serializable
data class KollekCategoryListDTO(
    val result: List<KollekCategoryDTO>? = listOf()
)

@Serializable
data class KollekCategoryDTO(
    val id: Int,
    val color: String,
    val name: String,
)


@Serializable
data class KollekCategoryDetailListDto(
    val result: List<KollekMineralDetailDTO>? = listOf()
)

