package dev.mysticmaster.tamngon247.ui.screen.home.admin.category

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.mysticmaster.tamngon247.R
import dev.mysticmaster.tamngon247.ui.components.CategoryManagerCardItem
import dev.mysticmaster.tamngon247.ui.dialog.category.CategoryAddDialog
import dev.mysticmaster.tamngon247.ui.theme.BackgroundColor
import dev.mysticmaster.tamngon247.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryManagerScreen(navController: NavController, categoryViewModel: CategoryViewModel) {
    val categoryState = categoryViewModel.categories.observeAsState(initial = emptyList())

    Log.d("TAG", "CategoryManagerScreen: ${categoryState.value}")

    val categories = categoryState.value
    var showDialogAdd by remember { mutableStateOf(false) }

    if (showDialogAdd) {
        CategoryAddDialog(
            categoryViewModel = categoryViewModel,
            onDismiss = { showDialogAdd = false },
        )
    }

    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.ArrowBackIosNew, contentDescription = "",
                                Modifier.clickable { navController.popBackStack() })
                            Image(
                                painter = painterResource(id = R.drawable.logotamngon),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxWidth(0.12f)
                            )
                            Text(text = "Tấm ngon 247")

                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BackgroundColor,
                        titleContentColor = Color.White,
                    ),

                    )
                Divider(thickness = 2.dp, color = Color.Black)
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialogAdd = true },
                contentColor = Color.White,
                containerColor = Color(0xFF2F2D2D)
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add new category")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .background(BackgroundColor)
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories.size) { index ->
                CategoryManagerCardItem(
                    categoryModel = categories[index],
                    index = index,
                    categoryViewModel = categoryViewModel
                )
            }
        }
    }
}