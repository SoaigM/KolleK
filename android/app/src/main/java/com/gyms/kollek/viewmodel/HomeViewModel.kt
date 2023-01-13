package com.gyms.kollek.viewmodel

import androidx.lifecycle.ViewModel


data class HomeViewModelState(
    var nbMineral: Int = 0,
    var valueCollection: Float = 0.0f,
)

class HomeViewModel() :  ViewModel()  {
    fun data() : HomeViewModelState {
        return HomeViewModelState(500, 32.5f)
    }

}