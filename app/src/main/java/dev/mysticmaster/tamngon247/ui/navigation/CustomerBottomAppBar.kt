package dev.mysticmaster.tamngon247.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.mysticmaster.tamngon247.ui.theme.BackgroundColor

@Composable
fun CustomerBottomAppBar(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor)) {
        Text(text = "Customer Home")
    }
}