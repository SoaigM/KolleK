package com.gyms.kollek.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


import androidx.navigation.NavHostController
import com.gyms.kollek.viewmodel.MineralDetailViewModelState
import com.gyms.kollek.viewmodel.MineralViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun KollekMineralDetailScreen(navHostController: NavHostController, id: Int) {



    val mineralViewModel = getViewModel<MineralViewModel>()
    val state by remember(mineralViewModel) {
        mineralViewModel.fetchMineralDetail(id)
    }.collectAsState(initial = MineralDetailViewModelState())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(Screen.KollekMineralList.route)}) {
                        Icon(
                            imageVector = Filled.ArrowBack,
                            contentDescription = "Back home"
                        )
                    }
                } ,
                actions = {
            })
        }
    ) {

        Column(modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(30.dp)) {

            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primary)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(30.dp)
            ) {
                item {

                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.onPrimary)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = state.image,
                            modifier = Modifier.padding(80.dp)
                        )
                    }
                        Text(
                            text = state.name,
                            modifier = Modifier.padding(20.dp),
                            style = MaterialTheme.typography.h4,
                            softWrap = false
                        )


                    Text(
                        text = "Category: "+state.category,
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        softWrap = false
                    )
                    Text(
                        text = "Origin: "+state.country,
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        softWrap = false
                    )
                    Text(
                        text = "Price: 0€",
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        softWrap = false
                    )
                }
            }
        }
    }
}
