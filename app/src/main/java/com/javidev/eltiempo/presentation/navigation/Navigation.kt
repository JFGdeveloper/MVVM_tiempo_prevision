package com.javidev.eltiempo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.javidev.eltiempo.presentation.screens.MainActivity
import com.javidev.eltiempo.presentation.screens.SplashScreen

@Composable
fun Navigation() {
    val controller = rememberNavController()

    NavHost(navController = controller, startDestination = DestinoScreen.SplashScreen.name){

        composable(DestinoScreen.SplashScreen.name){
            SplashScreen(controller)
        }

        composable(DestinoScreen.MainScreen.name){

        }
    }
}