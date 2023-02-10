package com.gyms.kollek.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gyms.kollek.components_old.WeatherDetailsListScreen
import com.gyms.kollek.viewmodel.LoginViewModel
import kotlinx.coroutines.Job
import org.koin.androidx.compose.getViewModel

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    val loginViewModel = getViewModel<LoginViewModel>()
    var job: Job? = null
    var errorLogin =false

    NavHost(
        navController = navHostController,
        startDestination = Screen.KollekList.route //startDestination = Screen.SplashScreen.route
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
                    errorLogin = !log.isLogged!!
                    if (log.isLogged == true) {
                        navHostController.navigate(Screen.KollekHome.route)
                    } else {
                        navHostController.navigate(Screen.KollekLogin.route)
                    }
                })
        }
        composable(
            route = Screen.KollekHome.route
        ) {
            KollekHomeScreen {
                when (it) {
                    0 -> navHostController.navigate(Screen.KollekLogin.route)
                    1 -> navHostController.navigate(Screen.KollekList.route)
                    2 -> println("MAP")
                }
            }
        }

        composable(
            route = Screen.KollekList.route
        ) {
            KollekListScreen {
                when (it) {
                    0 -> navHostController.navigate(Screen.KollekHome.route)
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object KollekLogin : Screen("KollekLogin")
    object KollekHome : Screen("KollekHome")
    object KollekList : Screen("KollekList")
    object SplashScreen : Screen("SplashScreen")
}