package com.javidev.eltiempo.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.javidev.eltiempo.R

@Composable
fun SplashScreen(controller: NavController) {

    Surface(modifier = Modifier
        .size(300.dp)
        .padding(14.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Icono Splash",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(95.dp)
            )

            Text(text = stringResource(id =R.string.app_name), style = MaterialTheme.typography.h6)
        }

    }
}

@Preview
@Composable
fun SplashPrev() {
    //Splas
}