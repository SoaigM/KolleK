package com.gyms.kollek.components

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.gyms.kollek.R
import com.gyms.kollek.components_old.FavIcon
import com.gyms.kollek.components_old.WeatherListRendererItem
import com.gyms.kollek.domain.WeatherItemDomain
import com.gyms.kollek.ui.theme.Typography
import com.gyms.kollek.viewmodel.HomeViewModel
import com.gyms.kollek.viewmodel.LoginViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun KollekHomeScreen() {

    val homeViewModel = getViewModel<HomeViewModel>()
    Column {
        Button(onClick = {}) {
            Text("Mon bouton")
        }
        // rest of the layout
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
                        onClick = { println("Collection") },
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
                        onClick = { println("MAP") },
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
                            text = data.valueCollection.toString(),
                            maxLines = 1,
                            fontSize = 30.sp,
                        )
                    }
                }

        }
    }
}