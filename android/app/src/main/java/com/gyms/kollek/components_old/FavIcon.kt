package com.gyms.kollek.components_old

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gyms.kollek.R
import com.gyms.kollek.domain.WeatherCityDomain
import com.gyms.kollek.viewmodel_old.FavViewModel
import com.gyms.kollek.viewmodel_old.FavViewModelState
import org.koin.androidx.compose.getViewModel

@Composable
fun FavIcon(
    city: WeatherCityDomain,
    modifier: Modifier
) {
    val favViewModel = getViewModel<FavViewModel>()

    val state by remember(favViewModel) {
        favViewModel.isInFav(city)
    }.collectAsState(initial = FavViewModelState())

    IconButton(onClick = { favViewModel.addOrRemoveCity(city) }, modifier = modifier) {
        Icon(
            imageVector = if (state.isInFav) {
                Icons.Default.Favorite
            } else Icons.Filled.FavoriteBorder,
            contentDescription = stringResource(id = R.string.fav_icon_description)
        )
    }
}