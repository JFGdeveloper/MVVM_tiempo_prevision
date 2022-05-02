package com.javidev.eltiempo.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.javidev.eltiempo.R
import com.javidev.eltiempo.presentation.navigation.DestinoScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(controller: NavController) {

    val defaultCity = "San Diego"
    val scale = remember {
        Animatable(0f)
    }

    AnimationSplash(scale,controller)

    Surface(modifier = Modifier
        .size(300.dp)
        .padding(14.dp)
        .scale( scale.value),
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

@Composable
private fun AnimationSplash(scale: Animatable<Float, AnimationVector1D>,controller: NavController) {
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )

        delay(2000L)
        controller.navigate(DestinoScreen.MainScreen.name)

    })
}

@Preview
@Composable
fun SplashPrev() {
    //Splas
}