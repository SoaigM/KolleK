package com.gyms.kollek.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.gyms.kollek.R


data class LoginViewModelState(
    var isLogged: Boolean? = false,
    var session: String? = null,
)

class LoginViewModel() :  ViewModel()  {
    fun login(log : String) : LoginViewModelState {

        return if(log=="user | pass"){
            LoginViewModelState(true, "a5sd65e16w4qsf354")
        } else{
            LoginViewModelState(false, null)
        }
    }

}