package dev.mysticmaster.tamngon247.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.InsertChart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.InsertChart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.mysticmaster.tamngon247.R
import dev.mysticmaster.tamngon247.ui.screen.home.admin.AdminExtraFeatureScreen
import dev.mysticmaster.tamngon247.ui.screen.home.admin.AdminHomeScreen
import dev.mysticmaster.tamngon247.ui.screen.home.admin.AdminProductManagerScreen
import dev.mysticmaster.tamngon247.ui.screen.home.admin.AdminStatisticsScreen
import dev.mysticmaster.tamngon247.util.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminBottomAppBar(navController: NavController) {
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    val navControllerNavigation = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                Column(Modifier.fillMaxWidth()) {
                    TopAppBar(
                        modifier = Modifier.height(40.dp),
                        title = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logotamngon),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(30.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Tấm Ngon 247", fontSize = 20.sp)

                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Black,
                            titleContentColor = Color.White,
                        )
                    )
                }
            },

            bottomBar = {
                Column(Modifier.fillMaxWidth()) {
                    BottomAppBar(
                        containerColor = Color.Black,
                        modifier = Modifier.height(50.dp)
                    ) {
                        IconButton(
                            onClick = {
                                selected.value = Icons.Default.Home
                                navControllerNavigation.navigate(Route.AdminHome.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                        {
                            Icon(
                                if (selected.value == Icons.Default.Home) Icons.Default.Home else Icons.Outlined.Home,
                                contentDescription = "",
                                modifier = Modifier.size(25.dp),
                                tint = Color.White
                            )

                        }

                        IconButton(
                            onClick = {
                                selected.value = Icons.Rounded.InsertChart
                                navControllerNavigation.navigate(Route.AdminStatistics.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                        {
                            Icon(
                                if (selected.value == Icons.Rounded.InsertChart) Icons.Rounded.InsertChart else Icons.Outlined.InsertChart,
                                contentDescription = "",
                                modifier = Modifier.size(25.dp),
                                tint = Color.White

                            )

                        }

                        IconButton(
                            onClick = {
                                selected.value = Icons.Rounded.Category
                                navControllerNavigation.navigate(Route.AdminProductManager.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                        {
                            Icon(
                                if (selected.value == Icons.Rounded.Category) Icons.Rounded.Category else Icons.Outlined.Category,
                                contentDescription = "",
                                modifier = Modifier.size(25.dp),
                                tint = Color.White

                            )
                        }

                        IconButton(
                            onClick = {
                                selected.value = Icons.Default.Person
                                navControllerNavigation.navigate(Route.AdminExtraFeature.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                        {
                            Icon(
                                if (selected.value == Icons.Default.Person) Icons.Default.Person else Icons.Outlined.Person,
                                contentDescription = "",
                                modifier = Modifier.size(25.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navControllerNavigation,
                startDestination = Route.AdminHome.screen,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Route.AdminHome.screen) { AdminHomeScreen() }
                composable(Route.AdminStatistics.screen) { AdminStatisticsScreen() }
                composable(Route.AdminProductManager.screen) { AdminProductManagerScreen(navController) }
                composable(Route.AdminExtraFeature.screen) { AdminExtraFeatureScreen() }
            }

        }
    }
}