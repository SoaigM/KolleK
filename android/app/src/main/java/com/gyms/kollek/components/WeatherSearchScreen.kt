package com.gyms.kollek.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.gyms.kollek.R
import com.gyms.kollek.ui.theme.Typography
import com.gyms.kollek.viewmodel.CityViewModel
import com.gyms.kollek.viewmodel.WeatherViewModel
import com.gyms.kollek.viewmodel.WeatherViewModelState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalUnitApi::class)
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun WeatherSearchScreen(
    navHostController: NavHostController
) {
    var job: Job? = null
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val weatherViewModel = getViewModel<WeatherViewModel>()
    var locationSearching by remember { mutableStateOf(false) }
    var state by remember {
        mutableStateOf(WeatherViewModelState())

    }
    Scaffold() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Blue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .background(color = Color.Yellow)
                        .border(width = 3.dp, color = Color.Black)
                ) {
                    Text("Alfred Sisley")
                    Text("3 minutes ago")
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .background(color = Color.Red)
                        .border(width = 3.dp, color = Color.Black)
                ) {
                    Text("TOMMTMMETMMT")
                    Text("12345678912347")
                }
            }
        }
    }
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Blue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.entry_label),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    style = Typography.h1,
                    fontStyle = FontStyle.Italic,
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(3.dp)
                        .background(color = Color.Red)
                        .border(width = 4.dp, color = Color.Black),
                ) {
                    item {
                        Column() {

                            Text(
                                text = stringResource(id = R.string.app_name),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(30.dp),
                                style = Typography.h4,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(20.dp)
                                .background(color = Color.Blue)
                                .border(width = 4.dp, color = Color.Black),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("World")
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(20.dp)
                                .background(color = Color.Yellow)
                                .border(width = 4.dp, color = Color.Black),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Hello")
                        }
                    }
                }
            }
        }
    }
}