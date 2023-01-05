package com.gyms.kollek.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gyms.kollek.services.WeatherResultDto

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherResult")
    fun getAll(): List<WeatherResultDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weatherResult: WeatherResultDto)

    @Query("DELETE FROM WeatherResult")
    fun deleteAll()
}

