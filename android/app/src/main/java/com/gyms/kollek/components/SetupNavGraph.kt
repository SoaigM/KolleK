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
            route = Screen.KollekMineralList.route
        ) {
            KollekListMineralScreen (navHostController = navHostController)
        }


        composable(
            route = "${Screen.KollekMineralDetail.route}/{mineralID}",
            arguments = listOf(navArgument("mineralID") { type = NavType.IntType })
        ) {
            it.arguments?.getInt("mineralID")
                ?.let { it1 -> KollekMineralDetailScreen(navHostController = navHostController, id= it1) }
        }


        composable(
            route = Screen.KollekCategoryList.route
        ) {
            KollekListCategoryScreen (navHostController = navHostController)
        }


        composable(
            route = "${Screen.KollekCategoryDetail.route}/{categoryID}",
            arguments = listOf(navArgument("categoryID") { type = NavType.IntType })
        ) {
            it.arguments?.getInt("categoryID")
                ?.let { it1 -> KollekCategoryDetailsScreen(navHostController = navHostController, id= it1) }
        }
    }
}

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object KollekLogin : Screen("KollekLogin")
    object KollekHome : Screen("KollekHome")
    object KollekMineralList : Screen("KollekList")
    object KollekMineralDetail : Screen("KollekMineralDetail")
    object KollekCategoryList : Screen("KollekCategoryList")
    object KollekCategoryDetail : Screen("KollekCategoryDetail")
}