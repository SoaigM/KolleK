package com.gyms.kollek.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import com.gyms.kollek.domain.KollekMineral
import com.gyms.kollek.domain.KollekResultMinerals
import com.gyms.kollek.domain.toDomain
import com.gyms.kollek.services.KollekAPI
import com.gyms.kollek.utils.Resource
import com.gyms.kollek.utils.Status
import com.gyms.kollek.viewmodel_old.WeatherViewModelState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.inject


data class MineralDetailViewModelState(
    var items: List<KollekMineral> = emptyList(),
    var isLoading: Boolean = false,
)

class MineralViewModel(private val api: KollekAPI) :  ViewModel()  {
    fun getMineralById(id : String) : KollekMineral {

        return KollekMineral(123,"topaze","France","image",3,"category name","category color")
    }




    fun fetchWithFlow() = flow {
        val flow = getMineralList()
        flow.collect {
            val state = MineralDetailViewModelState(
                items = it.data?.items ?: listOf(),
                isLoading = it.status == Status.LOADING
            )
            emit(
                state
            )
        }
    }


    fun getMineralList() : Flow<Resource<KollekResultMinerals?>> = flow {

        emit(Resource.Loading())
        emit(Resource.Success(api.fetchMinerals().toDomain()))


        //var a =MineralDetailViewModelState("123","topaze","France","image","category","category name","category color")
        //var b = MineralDetailViewModelState("124","test name","test country","test image","test category","test category name","test category color")
        //return arrayOf(a,b)
    }

}

