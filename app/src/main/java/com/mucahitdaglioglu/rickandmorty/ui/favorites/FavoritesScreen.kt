package com.mucahitdaglioglu.rickandmorty.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mucahitdaglioglu.rickandmorty.R
import com.mucahitdaglioglu.rickandmorty.data.local.CharacterDetail
import com.mucahitdaglioglu.rickandmorty.data.model.Location
import com.mucahitdaglioglu.rickandmorty.data.model.Origin
import com.mucahitdaglioglu.rickandmorty.data.model.Result
import com.mucahitdaglioglu.rickandmorty.ui.SharedViewModel
import com.mucahitdaglioglu.rickandmorty.ui.home.CardForImageBackground
import com.mucahitdaglioglu.rickandmorty.ui.navigation.Screen

@Composable
fun FavoritesScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val viewModel = hiltViewModel<FavoritesViewModel>()
    val list by rememberUpdatedState(newValue = viewModel.allFavorites.collectAsState(initial = emptyList()).value)


    FavoritesList(list = list, sharedViewModel = sharedViewModel, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesList(list: List<CharacterDetail>, sharedViewModel: SharedViewModel, navController: NavController) {

    Column {
        TopAppBar(title = { Text(text = stringResource(id = R.string.favorites)) })

        if (list.isEmpty()) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f),
                    alignment = Alignment.Center,
                    painter = painterResource(id = R.drawable.ic_nofavorite),
                    contentDescription = stringResource(id = R.string.no_favorite)
                )
            }

        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(list.size) { index ->

                    CardForImageBackground(
                        modifier = Modifier
                            .height(300.dp)
                            .padding(4.dp)
                            .clickable {
                                sharedViewModel.addResult(
                                    Result(
                                        null,
                                        null,
                                        list[index].gender,
                                        list[index].id,
                                        list[index].image,
                                        Location(list[index].locationName, null),
                                        list[index].name,
                                        Origin(list[index].originName, null),
                                        list[index].species,
                                        list[index].status,
                                        null,
                                        null
                                    )
                                )

                                navController.navigate(Screen.DetailScreen.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }

                            },
                        image = list[index].image,
                        name = list[index].name

                    )
                }
            }
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun preview() {

}