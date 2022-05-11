package com.javidev.eltiempo.presentation.navigation

import MainScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.javidev.eltiempo.presentation.screens.SplashScreen
import com.javidev.eltiempo.presentation.screens.aboutScreen.AboutScreen
import com.javidev.eltiempo.presentation.screens.favoritesScreen.FavoritesScreen
import com.javidev.eltiempo.presentation.screens.mainScreen.MainViewModel
import com.javidev.eltiempo.presentation.screens.searchScreen.SearchScreen
import com.javidev.eltiempo.presentation.screens.settingsScreen.SettingsScreen

@ExperimentalComposeUiApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DestinoScreen.SplashScreen.name
    ) {
        composable(DestinoScreen.SplashScreen.name) {
            SplashScreen(controller = navController)
        }

        //www.google.com/cityname="seattle"
        val route = DestinoScreen.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(navArgument(name = "city") {
                    type = NavType.StringType
            })
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController, mainViewModel,
                    city = city
                )
            }
        }

        composable(DestinoScreen.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(DestinoScreen.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(DestinoScreen.SettingScreen.name) {
            SettingsScreen(navController = navController)
        }

        composable(DestinoScreen.FavoriteScreen.name) {
            FavoritesScreen(navController = navController)
        }

    }
}