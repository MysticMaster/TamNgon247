package dev.mysticmaster.tamngon247.ui.screen.home.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.mysticmaster.tamngon247.ui.theme.BackgroundColor

@Composable
fun AdminExtraFeatureScreen(){
    Column(
        modifier = Modifier.background(BackgroundColor)
            .fillMaxSize()
    ) {
        Text(text = "Extra", color = Color.White)
    }
}