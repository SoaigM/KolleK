package com.gyms.kollek.viewmodel

import androidx.lifecycle.ViewModel
import com.gyms.kollek.domain.*
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

data class CategoryListViewModelState(
    var items: List<KollekCategory> = emptyList(),
    var isLoading: Boolean = false,
)

data class CategoryDetailListViewModelState(
    var items: List<KollekMineralDetail> = emptyList(),
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

    fun fetchCategoryList() = flow {
        val flow = getCategoryList()
        flow.collect {
            val state = CategoryListViewModelState(
                items = it.data?.items ?: listOf(),
                isLoading = it.status == Status.LOADING
            )
            emit(
                state
            )
        }
    }

    fun fetchCategoryDetail(id: Int) = flow {
        val flow = getCategoryDetail(id)
        flow.collect {
            val state = CategoryDetailListViewModelState(
                items = it.data?.items ?: listOf(),
                isLoading = it.status == Status.LOADING
            )
            emit(
                state
            )
        }
    }





    fun getMineralList() : Flow<Resource<KollekMineralList?>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(api.fetchMineralsList().toDomain()))
    }

    fun getMineralDetail(id: Int) : Flow<Resource<KollekMineralDetail?>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(api.fetchMineralDetail(id).toDomain()))
    }

    fun getCategoryList() : Flow<Resource<KollekCategoryList?>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(api.fetchCategoryList().toDomain()))
    }

    fun getCategoryDetail(id: Int) : Flow<Resource<KollekCategoryDetailList?>> = flow {
        emit(Resource.Loading())
        emit(Resource.Success(api.fetchCategoryDetail(id).toDomain()))
    }

}

