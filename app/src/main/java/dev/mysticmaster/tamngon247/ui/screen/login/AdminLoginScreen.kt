package dev.mysticmaster.tamngon247.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.mysticmaster.tamngon247.R
import dev.mysticmaster.tamngon247.ui.components.LoginCard
import dev.mysticmaster.tamngon247.ui.theme.BackgroundColor
import dev.mysticmaster.tamngon247.util.Route
import dev.mysticmaster.tamngon247.viewmodel.AdminLoginViewModel
import dev.mysticmaster.tamngon247.viewmodel.LoginViewModel

@Composable
fun AdminLoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val adminLoginViewModel = AdminLoginViewModel()
    val nowRoute = Route.AdminLogin
    val nextRoute = Route.AdminBottomAppBar

    val snackbarHostState = remember { SnackbarHostState() }
    HandleLoginState(snackbarHostState, loginViewModel, navController, nextRoute, nowRoute)

    HandleGoToCustomerLoginState(
        adminLoginViewModel = adminLoginViewModel,
        navController = navController
    )
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.logotamngon),
                contentDescription = "logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Đăng Nhập",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            LoginCard(
                loginViewModel = loginViewModel,
                typeLogin = false
            )
            Spacer(modifier = Modifier.height(30.dp))
            GoToCustomerLoginButton {
                adminLoginViewModel.goToCustomerLogin()
            }
        }
    }
}
@Composable
private fun HandleLoginState(
    snackbarHostState: SnackbarHostState,
    loginViewModel: LoginViewModel,
    navController: NavController,
    nextRoute: Route,
    nowRoute: Route
) {
    val isAuthenticated by loginViewModel.isAdminAuthenticated.observeAsState(initial = 0L)
    LaunchedEffect(key1 = isAuthenticated) {
        when (isAuthenticated) {
            200L -> {
                navController.navigate(nextRoute.screen) {
                    popUpTo(nowRoute.screen) { inclusive = true }
                }
                loginViewModel.resetAdminAuthenticationState()
            }

            400L -> {
                snackbarHostState.showSnackbar(
                    message = "Tài khoản và mật khẩu không chính xác",
                    duration = SnackbarDuration.Short,
                )
                loginViewModel.resetAdminAuthenticationState()
            }

            404L -> {
                snackbarHostState.showSnackbar(
                    message = "Tên đăng nhập không tồn tại",
                    duration = SnackbarDuration.Short,
                )
                loginViewModel.resetAdminAuthenticationState()
            }

            null -> {}
        }
    }
}


@Composable
private fun HandleGoToCustomerLoginState(
    adminLoginViewModel: AdminLoginViewModel,
    navController: NavController
) {
    val isGoToCustomerLogin by adminLoginViewModel.isGoToCustomerLogin.observeAsState()
    LaunchedEffect(key1 = isGoToCustomerLogin) {
        when (isGoToCustomerLogin) {
            true -> {
                navController.navigate(Route.CustomerLogin.screen) {
                    popUpTo(Route.AdminLogin.screen) { inclusive = true }
                }
            }

            false -> {}

            null -> {}
        }
    }
}

@Composable
private fun GoToCustomerLoginButton(onClick: () -> Unit) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Trở lại giao diện chính")
            }
        },
        modifier = Modifier.clickable { onClick() }
    )
}