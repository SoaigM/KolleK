package com.gyms.kollek.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close


import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun KollekListScreen(onButtonClicked: (Int) -> Unit) {
    val listeDeMots = listOf("mot1", "mot2", "mot3","mot1", "mot2", "mot3","mot1", "mot2", "mot3","mot1", "mot2", "mot3")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "text1")
                },
                navigationIcon = {
                    IconButton(onClick = { println("KollekListScreen")
                        onButtonClicked(0)}) {
                        Icon(
                            imageVector = Filled.ArrowBack,
                            contentDescription = "Back home"
                        )
                    }
                } ,
                actions = {
                /*WeatherSearchBar(
                    searchText = text,
                    onLocateSearching = {
                        locationSearching = it
                    },
                    onLocateChange = {
                        CoroutineScope(Dispatchers.IO).launch {
                            weatherViewModel.locationChanged(it).collect {
                                state = it
                            }
                        }
                    },
                    placeholderText = stringResource(id = R.string.search_placeholder),
                    onSearchTextChanged = { it ->
                        text = it
                        job?.cancel()
                        job = CoroutineScope(Dispatchers.IO).launch {
                            weatherViewModel.cityChanged(text).collect {
                                state = it
                            }
                        }
                    },
                    onClearClick = {
                        text = ""
                        state = WeatherViewModelState()
                    }
                )*/
            })
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
        ) {
            items(items = listeDeMots) { name ->

                RockColumn(name = name)
            }
        }
    }
}

@Composable
fun RockColumn(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Button(
                onClick = { println(name) },
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Value, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Filled.Add else Filled.Close,
                    contentDescription = if (expanded) {
                        "show_less"
                    } else {
                        "show_more"
                    }
                )
            }
        }
    }
}
