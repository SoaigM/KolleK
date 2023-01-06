package com.gyms.kollek.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gyms.kollek.components_old.WeatherDetailsListScreen
import com.gyms.kollek.viewmodel.LoginViewModel
import kotlinx.coroutines.Job
import org.koin.androidx.compose.getViewModel
import com.gyms.kollek.R

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    val loginViewModel = getViewModel<LoginViewModel>()
    var job: Job? = null
    var errorLogin =false

    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.KollekLogin.route
        ) {
            KollekLoginScrean(errorMessage = errorLogin,
                onLoginClicked = {
                    val log = loginViewModel.login(it)
                    errorLogin= !log.isLogged!!
                    if(log.isLogged == true){
                        navHostController.navigate(Screen.SplashScreen.route)
                    }else{
                        navHostController.navigate(Screen.KollekLogin.route)
                    }
                })
        }
        composable(
            route = "${Screen.WeatherList.route}/{cityName}",
            arguments = listOf(navArgument("cityName") { type = NavType.StringType })
        ) {
            WeatherDetailsListScreen(
                navHostController = navHostController,
                cityName = it.arguments?.getString("cityName") ?: ""
            )
        }
    }
}

sealed class Screen(val route: String) {
    object KollekLogin : Screen("WeatherSearch")
    object WeatherList : Screen("WeatherList")
    object SplashScreen : Screen("SplashScreen")
}