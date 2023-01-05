package com.gyms.kollek.repository

import com.gyms.kollek.data.FavCity
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepositoryInterface {
    val favsCities : Flow<List<FavCity>>

    suspend fun addOrRemoveFavCity(favCity : FavCity)

    suspend fun isInFavs(favCity : FavCity): Flow<Boolean>
}