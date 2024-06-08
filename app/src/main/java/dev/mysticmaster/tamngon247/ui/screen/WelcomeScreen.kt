package dev.mysticmaster.tamngon247.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import dev.mysticmaster.tamngon247.R
import dev.mysticmaster.tamngon247.ui.theme.BackgroundColor
import dev.mysticmaster.tamngon247.util.Route
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavController){

    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate(Route.CustomerLogin.screen) {
            popUpTo(Route.Welcome.screen) { inclusive = true }
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.7f),
            painter =  painterResource(id = R.drawable.logotamngon),
            contentDescription ="logo",
            contentScale = ContentScale.FillWidth
        )

    }
}