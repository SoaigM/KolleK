package com.gyms.kollek.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gyms.kollek.viewmodel.CategoryDetailListViewModelState
import com.gyms.kollek.viewmodel.MineralViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun KollekCategoryDetailsScreen(navHostController: NavHostController, id: Int) {
    //val listeDeMots = listOf("mot1", "mot2", "mot3","mot1", "mot2", "mot3","mot1", "mot2", "mot3","mot1", "mot2", "mot3")

    val mineralViewModel = getViewModel<MineralViewModel>()
    val state by remember(mineralViewModel) {
        mineralViewModel.fetchCategoryDetail(id)
    }.collectAsState(initial = CategoryDetailListViewModelState())





    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Minerals from category")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(Screen.KollekCategoryList.route)}) {
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
        LazyColumn(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
        ) {
            items(items = state.items) { list ->
                RockColumnCategoryDetail(
                    mineralDetails = {
                        navHostController.navigate(route = "${Screen.KollekMineralDetail.route}/${it}")
                    },
                    id = list.id,
                    name = list.name
                )
            }
        }
    }
}

@Composable
fun RockColumnCategoryDetail(mineralDetails: (Int) -> Unit, id: Int, name: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row {
            Button(
                onClick = {
                    println("here $id")
                    mineralDetails(id) },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "name: ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
    }
}
