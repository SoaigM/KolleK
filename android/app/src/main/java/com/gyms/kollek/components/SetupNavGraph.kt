package com.gyms.kollek.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            KollekHomeScreen (navHostController = navHostController)
        }

        composable(
            route = Screen.KollekList.route
        ) {
            KollekListScreen (navHostController = navHostController)
        }


        composable(
            route = "${Screen.KollekMineralDetail.route}/{mineralID}",
            arguments = listOf(navArgument("mineralID") { type = NavType.StringType })
        ) {
            it.arguments?.getString("mineralID")
                ?.let { it1 -> KollekMineralDetail(navHostController = navHostController, name= it1) }
        }
    }
}

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object KollekLogin : Screen("KollekLogin")
    object KollekHome : Screen("KollekHome")
    object KollekList : Screen("KollekList")
    object KollekMineralDetail : Screen("KollekMineralDetail")
}