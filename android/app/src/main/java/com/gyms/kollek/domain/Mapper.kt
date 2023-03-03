package com.gyms.kollek.domain

import com.gyms.kollek.services.*


fun KollekMineralListDto.toDomain() = KollekMineralList(
    items = this.result?.map { it.toDomain() },
)


fun KollekMineralDTO.toDomain() = KollekMineral(
    image = this.image,
    id=this.id,
    name=this.name,
    country = this.country,
    categoryId = this.categoryId,
    categoryName = this.categoryName,
    categoryColor = this.categoryColor
)


fun KollekMineralDetailDTO.toDomain() = KollekMineralDetail(
    image = this.image,
    id=this.id,
    name=this.name,
    country = this.country,
    category = this.category
)

fun KollekCategoryListDTO.toDomain() = KollekCategoryList(
    items = this.result?.map { it.toDomain() },
)

fun KollekCategoryDTO.toDomain() = KollekCategory(
     id = this.id,
     name = this.name,
     color = this.color,
)


fun KollekCategoryDetailListDto.toDomain() = KollekCategoryDetailList(
    items = this.result?.map { it.toDomain() },
)
