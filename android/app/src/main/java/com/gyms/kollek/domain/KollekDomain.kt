package com.gyms.kollek.domain

data class KollekMineralList(
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


data class KollekMineralDetail(
    val category: Int,
    val country: String,
    val id: Int,
    val image: String,
    val name: String,
)


data class KollekCategoryList(
    val items: List<KollekCategory>? = listOf()
)

data class KollekCategory(
    val id: Int,
    val color: String,
    val name: String,
)


data class KollekCategoryDetailList(
    val items: List<KollekMineralDetail>? = listOf()
)