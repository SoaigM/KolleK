package com.gyms.kollek.viewmodel_old

import android.location.Location
import androidx.lifecycle.ViewModel
import com.gyms.kollek.domain.WeatherCityDomain
import com.gyms.kollek.domain.WeatherItemDomain
import com.gyms.kollek.repository.WeatherRepositoryInterface
import com.gyms.kollek.utils.Status
import kotlinx.coroutines.flow.flow

data class WeatherViewModelState(
    var city: WeatherCityDomain? = null,
    var first: WeatherItemDomain? = null,
    var items: List<WeatherItemDomain> = emptyList(),
    var isLoading: Boolean = false,
)

class WeatherViewModel(private val repository: WeatherRepositoryInterface) :
    ViewModel() {

    fun cityChanged(value: String) = fetchWeather(value)

    fun locationChanged(value: Location) = fetchWeather(value)

    private fun fetchWeather(location: Location) = fetchWithFlow(null, location)

    private fun fetchWeather(city: String) = fetchWithFlow(city)

    private fun fetchWithFlow(city: String? = null, location: Location? = null) = flow {
        val flow = if (city != null) repository.fetchWeather(city) else repository.fetchWeather(
            location!!.latitude,
            location!!.longitude
        )
        flow.collect {
            val state = WeatherViewModelState(
                city = it.data?.city,
                first = it.data?.items?.firstOrNull(),
                items = it.data?.items ?: listOf(),
                isLoading = it.status == Status.LOADING
            )
            emit(
                state
            )
        }
    }
}
