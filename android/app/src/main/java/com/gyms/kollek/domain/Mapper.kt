package com.gyms.kollek.domain

import com.gyms.kollek.services.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun KollekResultDto.toDomain() = KollekResultMinerals(
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
