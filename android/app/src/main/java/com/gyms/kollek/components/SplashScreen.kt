package com.gyms.kollek.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gyms.kollek.R
import com.gyms.kollek.components.Screen

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SplashScreen(navHostController: NavHostController) {
    val raw = R.raw.papa_meteo
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(raw))
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress }
        )
    }

    if(logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
        navHostController.navigate(route = "${Screen.WeatherSearch.route}"){
        //suppression de la backstack
            popUpTo(Screen.SplashScreen.route){
                inclusive = true
            }
        }
    }
}