package com.gyms.kollek.components

import android.content.ClipData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


import androidx.navigation.NavHostController
import com.gyms.kollek.viewmodel.MineralViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun KollekMineralDetail(navHostController: NavHostController, name: String) {

    val mineralViewModel = getViewModel<MineralViewModel>()
    val mineral = name.let { mineralViewModel.getMineralById(it) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(Screen.KollekList.route)}) {
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
                    Row(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.primary)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .background(color = MaterialTheme.colors.onPrimary)
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = mineral.image,
                                modifier = Modifier.padding(50.dp)
                            )
                        }
                        Text(
                            text = mineral.name,
                            modifier = Modifier.padding(20.dp),
                            style = MaterialTheme.typography.h4,
                            softWrap = false
                        )
                    }

                    Text(
                        text = "Category: "+mineral.categoryName,
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        softWrap = false
                    )
                    Text(
                        text = "Origin: "+mineral.country,
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
