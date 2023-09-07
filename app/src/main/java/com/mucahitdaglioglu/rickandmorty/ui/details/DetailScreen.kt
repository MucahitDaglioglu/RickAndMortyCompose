package com.mucahitdaglioglu.rickandmorty.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mucahitdaglioglu.rickandmorty.R
import com.mucahitdaglioglu.rickandmorty.data.local.CharacterDetail
import com.mucahitdaglioglu.rickandmorty.ui.SharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    
    Scaffold(bottomBar = {  },
        content = { padding ->
            Column(modifier = Modifier
                .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally) {

                TopAppBar(title = { Text(text = stringResource(id = R.string.character_detail)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
                        }
                    }
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.99f)
                        .fillMaxHeight(0.5f),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    GlideImage(
                        model = sharedViewModel.result?.image,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                CardCharacterDetail(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .fillMaxHeight(0.95f),
                    sharedViewModel.result?.id,
                    sharedViewModel.result?.image,
                    sharedViewModel.result?.name,
                    sharedViewModel.result?.gender,
                    sharedViewModel.result?.status,
                    sharedViewModel.result?.location?.name,
                    sharedViewModel.result?.species,
                    sharedViewModel.result?.origin?.name
                )
            }
    })

}

@Composable
fun CardCharacterDetail(modifier: Modifier, id: Int?, image:String?, name: String?, gender: String?, status: String?, locationName: String?, species: String?, originName: String?) {

    var checked by remember {
        mutableStateOf(false)
    }

    val viewModel = hiltViewModel<DetailsViewModel>()

    LaunchedEffect(true) {
        viewModel.viewModelScope.launch {
            viewModel.allFavorites.collect {
                for (i in it) {
                    if (i.id == id) {
                        checked = true
                    }
                }
            }
        }
    }

    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Text(modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp), text = name ?: "", fontSize = 24.sp, fontWeight = FontWeight.W500)

                IconToggleButton(
                    modifier = Modifier.clickable {},
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        if (it) {
                            viewModel.viewModelScope.launch {
                                viewModel.insertToFavorite(CharacterDetail(id,image, name, gender, status, locationName, species, originName))
                            }
                        } else {
                            viewModel.viewModelScope.launch {
                                viewModel.deleteFromFavorite(CharacterDetail(id,image, name, gender, status, locationName, species, originName))
                            }
                        }
                    }
                ) {
                    Icon(modifier = Modifier.size(48.dp),
                        imageVector = if (checked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                    )
                }

            }

            Column(modifier =  Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.gender), modifier = Modifier, fontWeight = FontWeight.W500)
                    Text(text = gender ?: "", modifier = Modifier, fontSize = 18.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.status), modifier = Modifier, fontWeight = FontWeight.W500)
                    Text(text = status ?: "", modifier = Modifier, fontSize = 18.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.location), modifier = Modifier, fontWeight = FontWeight.W500)
                    Text(text = locationName ?: "", modifier = Modifier, fontSize = 18.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.species), modifier = Modifier, fontWeight = FontWeight.W500)
                    Text(text = species ?: "", modifier = Modifier, fontSize = 18.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.origin), modifier = Modifier, fontWeight = FontWeight.W500)
                    Text(text = originName ?: "", modifier = Modifier, fontSize = 18.sp)
                }
            }
        }
    }

}


@Composable
@Preview
fun Preview() {

}
