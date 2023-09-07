package com.mucahitdaglioglu.rickandmorty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mucahitdaglioglu.rickandmorty.ui.SharedViewModel
import com.mucahitdaglioglu.rickandmorty.ui.details.DetailScreen
import com.mucahitdaglioglu.rickandmorty.ui.favorites.FavoritesScreen
import com.mucahitdaglioglu.rickandmorty.ui.home.HomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier, navController: NavHostController) {

    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(modifier = modifier, navController = navController, startDestination = BottomNavItem.Home.route) {

        composable(BottomNavItem.Home.route) {
            HomeScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(Screen.DetailScreen.route) {
            DetailScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(BottomNavItem.Favorites.route) {
            FavoritesScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

    }

}

