package dev.mysticmaster.tamngon247.ui.navhost


import android.content.Context
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.mysticmaster.tamngon247.ui.navigation.AdminBottomAppBar
import dev.mysticmaster.tamngon247.ui.navigation.CustomerBottomAppBar
import dev.mysticmaster.tamngon247.ui.screen.WelcomeScreen
import dev.mysticmaster.tamngon247.ui.screen.home.admin.category.CategoryManagerScreen
// import dev.mysticmaster.tamngon247.ui.screen.home.admin.dish.DishManagerScreen
import dev.mysticmaster.tamngon247.ui.screen.login.AdminLoginScreen
import dev.mysticmaster.tamngon247.ui.screen.login.CustomerLoginScreen
import dev.mysticmaster.tamngon247.util.Route
import dev.mysticmaster.tamngon247.viewmodel.CategoryViewModel
//import dev.mysticmaster.tamngon247.viewmodel.DishViewModel

@Composable
fun AppNavHost(context: Context) {
    val navController = rememberNavController()
    val categoryViewModel = CategoryViewModel(context)
//    val dishViewModel = DishViewModel()

    NavHost(
        navController = navController,
        //startDestination = Route.Welcome.screen
        startDestination = Route.AdminBottomAppBar.screen
    ) {
        composable(Route.Welcome.screen) { WelcomeScreen(navController) }
        composable(Route.CustomerLogin.screen) { CustomerLoginScreen(navController = navController) }
        composable(Route.AdminLogin.screen) { AdminLoginScreen(navController) }
        composable(Route.CustomerBottomAppBar.screen) { CustomerBottomAppBar() }
        composable(Route.AdminBottomAppBar.screen) { AdminBottomAppBar(navController) }
        composable(Route.CategoryManager.screen) { CategoryManagerScreen(navController,categoryViewModel) }
//        composable(Route.DishManager.screen) { DishManagerScreen(navController,dishViewModel) }
    }

}