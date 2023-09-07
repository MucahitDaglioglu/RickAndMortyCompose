package com.mucahitdaglioglu.rickandmorty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mucahitdaglioglu.rickandmorty.ui.navigation.BottomNavItem
import com.mucahitdaglioglu.rickandmorty.ui.navigation.Navigation
import com.mucahitdaglioglu.rickandmorty.ui.theme.RickAndMortyTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RickAndMortyTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {

                    val items = listOf(BottomNavItem.Home, BottomNavItem.Favorites)

                    val navController = rememberNavController()
                    val showBottomBar = navController
                        .currentBackStackEntryAsState().value?.destination?.route in items.map { it.route }

                    Scaffold(
                        bottomBar = {
                            if (showBottomBar) {
                                BottomNavigationBar(
                                    items = items,
                                    navController = navController,
                                    onItemClick = {
                                        navController.navigate(it.route) {

                                            navController.graph.startDestinationRoute?.let { route ->
                                                popUpTo(route) {
                                                    saveState = true
                                                }
                                            }
                                            launchSingleTop = true
                                            restoreState = true

                                        }
                                    }
                                )
                            }

                        }
                    ) { innerPadding ->

                        Navigation(modifier = Modifier.padding(innerPadding), navController = navController)
                    }
                }


            }

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val backRoute = backStackEntry.value?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.route == backRoute,
                onClick = {
                   onItemClick(item)
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    BadgedBox(
                        badge = {
                        }
                    ) {
                        Icon(imageVector = if (item.route == backStackEntry.value?.destination?.route) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                                contentDescription = item.title
                        )
                    }
                }
            )
        }
    }

}











