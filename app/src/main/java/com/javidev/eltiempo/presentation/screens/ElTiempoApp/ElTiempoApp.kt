package com.javidev.eltiempo.presentation.screens.ElTiempoApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.javidev.eltiempo.presentation.navigation.Navigation
import com.javidev.eltiempo.presentation.ui.theme.ElTiempoTheme

@ExperimentalComposeUiApi
@Composable
fun ElTiempoApp() {
    ElTiempoTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Navigation()
            }

        }
    }
}