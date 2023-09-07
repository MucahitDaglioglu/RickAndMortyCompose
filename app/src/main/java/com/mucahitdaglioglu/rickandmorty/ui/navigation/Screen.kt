package com.mucahitdaglioglu.rickandmorty.ui.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("HomeScreen")
    object DetailScreen: Screen("DetailScreen")
    object FavoritesScreen: Screen("FavoritesScreen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
