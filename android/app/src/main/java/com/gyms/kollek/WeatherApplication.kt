    package com.gyms.kollek

    import android.app.Application
    import com.gyms.kollek.core.appModule
    import com.gyms.kollek.core.commonModule
    import org.koin.android.ext.koin.androidContext
    import org.koin.core.context.startKoin

    class WeatherApplication : Application() {
        companion object {
            var instance: WeatherApplication? = null
        }

        override fun onCreate() {
            instance = this
            super.onCreate()
            startKoin {
                androidContext(this@WeatherApplication)
                modules(appModule)
                modules(commonModule)
            }
        }
    }