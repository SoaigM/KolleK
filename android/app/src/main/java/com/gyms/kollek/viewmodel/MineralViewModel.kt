package com.gyms.kollek.viewmodel

import androidx.lifecycle.ViewModel
import com.gyms.kollek.domain.KollekMineral
import com.gyms.kollek.domain.KollekMineralDetail
import com.gyms.kollek.domain.KollekResultMinerals
import com.gyms.kollek.domain.toDomain
import com.gyms.kollek.services.KollekAPI
import com.gyms.kollek.utils.Resource
import com.gyms.kollek.utils.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


data class MineralListViewModelState(
    var items: List<KollekMineral> = emptyList(),
    var isLoading: Boolean = false,
)

data class MineralDetailViewModelState(
    val category: Int = 0,
    val country: String = "null",
    val id: Int = 0,
    val image: String = "null",
    val name: String = "null",
    var isLoading: Boolean = false,
)

class MineralViewModel(private val api: KollekAPI) :  ViewModel()  {
    fun getMineralById(id : String) : KollekMineral {

        return KollekMineral(123,"topaze","France","image",3,"category name","category color")
    }




    fun fetchMineralList() = flow {
        val flow = getMineralList()
        flow.collect {
            val state = MineralListViewModelState(
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
    }

    fun fetchMineralDetail(id: Int) = flow {
        val flow = getMineralDetail(id)
        flow.collect {
            val state = MineralDetailViewModelState(
                category = it.data?.category ?: 0,
                country = it.data?.country ?: "null",
                id = it.data?.id ?: 0,
                image = it.data?.image ?: "null",
                name = it.data?.name ?: "null",
                isLoading = it.status == Status.LOADING
            )
            emit(
                state
            )
        }
    }


    fun getMineralDetail(id: Int) : Flow<Resource<KollekMineralDetail?>> = flow {

        emit(Resource.Loading())
        emit(Resource.Success(api.fetchMineralDetail(id).toDomain()))
    }

}

