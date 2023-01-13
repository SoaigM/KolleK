package com.gyms.kollek.core

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import com.gyms.kollek.WeatherApplication
import com.gyms.kollek.data.WeatherRoomDb
import com.gyms.kollek.repository.UserPreferencesRepositoryImpl
import com.gyms.kollek.repository.UserPreferencesRepositoryInterface
import com.gyms.kollek.repository.WeatherRepository
import com.gyms.kollek.repository.WeatherRepositoryInterface
import com.gyms.kollek.services.OpenWeatherApi
import com.gyms.kollek.viewmodel_old.FavViewModel
import com.gyms.kollek.viewmodel.LoginViewModel
import com.gyms.kollek.viewmodel_old.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.gyms.kollek.viewmodel.HomeViewModel
import com.gyms.kollek.viewmodel_old.LocationViewModel
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<String>(named("weather_api_key")) { "888f70e84a4d7e44f3c0d4870c926e9d" }
    viewModel { WeatherViewModel(repository = get()) }
    viewModel { FavViewModel(userPreferences = get()) }
    viewModel{LoginViewModel()}
    viewModel{ HomeViewModel() }
    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(requireApplication.applicationContext)
    }
    viewModel { LocationViewModel(client =  get()) }
}
val commonModule = module {
    single { Android.create() }
    single { createJson() }
    single { createHttpClient(get(), get(), enableNetworkLogs = true) }
    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
    val database by lazy { WeatherRoomDb.getDatabase(requireApplication) }
    single<WeatherRepositoryInterface> { WeatherRepository(get(), database.weatherDao()) }
    single { OpenWeatherApi(get(), get(named("weather_api_key"))) }
    single<UserPreferencesRepositoryInterface> { UserPreferencesRepositoryImpl(requireApplication.dataStore) }
}



private inline val requireApplication
    get() = WeatherApplication.instance ?: error("Missing call: initWith(application)")

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) =
    HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.ALL
            }
        }
    }

class CustomHttpLogger() : Logger {
    override fun log(message: String) {
        Log.i("CustomHttpLogger", "message : $message")
    }
}
