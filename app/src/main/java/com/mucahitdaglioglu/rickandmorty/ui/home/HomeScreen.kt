package com.mucahitdaglioglu.rickandmorty.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mucahitdaglioglu.rickandmorty.R
import com.mucahitdaglioglu.rickandmorty.ui.SharedViewModel
import com.mucahitdaglioglu.rickandmorty.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val viewModel = hiltViewModel<HomeViewModel>()
    val items = viewModel.result.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))})

        when (items.loadState.refresh) {
            is LoadState.Loading -> {
                Column(modifier = Modifier.fillMaxSize(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.5f))
                }
            }
            else -> {}
        }


        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(items.itemCount) { index ->

                CardForImageBackground(
                    modifier = Modifier
                        .height(300.dp)
                        .padding(4.dp)
                        .clickable {

                            sharedViewModel.addResult(newResult = items[index])
                            navController.navigate(Screen.DetailScreen.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        inclusive = false
                                    }
                                }
                                // Avoid multiple copies of the same destination when re-selecting the same item
                                launchSingleTop = true
                                // Restore state when re-selecting a previously selected item
                                restoreState = true
                            }

                        },

                    image = items[index]?.image,
                    name = items[index]?.name
                )

                when (items.loadState.append) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.5f))
                    }
                    else -> {}
                }

            }

        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardForImageBackground(modifier: Modifier, image: String?, name: String?) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(10.dp)
    ) {

        GlideImage(
            modifier = Modifier
                .fillMaxHeight(0.8f),
            model = image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Text(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically),
            text = name ?: "",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

}

