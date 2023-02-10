package com.gyms.kollek.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gyms.kollek.R
import com.gyms.kollek.ui.theme.Typography
import com.gyms.kollek.viewmodel.HomeViewModel
import org.koin.androidx.compose.getViewModel




@Composable
fun KollekHomeScreen(navHostController: NavHostController) {

    val homeViewModel = getViewModel<HomeViewModel>()

    val collection by remember { mutableStateOf(false) }

    Column (modifier=Modifier.background(color=MaterialTheme.colors.background)){
        Row {
            Button(
                onClick = {
                    navHostController.navigate(Screen.KollekLogin.route)
                },

                modifier = Modifier
                    .align(alignment = Alignment.Top)
                    .size(40.dp, 35.dp)
            ) {
                Text("X")
            }
        }

    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = stringResource(id = R.string.app_name),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            style = Typography.h2,
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Button(
                    onClick = {navHostController.navigate(Screen.KollekList.route)},
                    modifier = Modifier
                        .size(300.dp, 150.dp)
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.button_collection),
                        fontSize = 20.sp,

                        )
                }

                Button(
                    onClick = { println("map")},
                    modifier = Modifier
                        .size(300.dp, 150.dp)
                        .padding(10.dp)
                ) {

                    Text(
                        text = stringResource(id = R.string.button_map),
                        fontSize = 20.sp,
                    )
                }

                var data = homeViewModel.data();

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(id = R.string.nb_mineral),
                    )
                    Text(
                        text = data.nbMineral.toString(),
                        maxLines = 1,
                        fontSize = 30.sp,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.value_mineral),
                    )
                    Text(
                        text = data.valueCollection.toString()+" €",
                        maxLines = 1,
                        fontSize = 30.sp,
                    )
                }
            }

        }
    }
    }
}
