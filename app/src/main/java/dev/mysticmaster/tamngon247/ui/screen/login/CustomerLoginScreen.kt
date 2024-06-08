package dev.mysticmaster.tamngon247.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import dev.mysticmaster.tamngon247.viewmodel.CustomerLoginViewModel
import dev.mysticmaster.tamngon247.viewmodel.LoginViewModel

@Composable
fun CustomerLoginScreen(navController: NavController) {
    val loginViewModel = LoginViewModel()
    val customerLoginViewModel = CustomerLoginViewModel()
    val nowRoute = Route.CustomerLogin
    val nextRoute = Route.CustomerBottomAppBar

    HandleGoToAdminLoginState(
        customerLoginViewModel = customerLoginViewModel,
        navController = navController
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.logotamngon),
            contentDescription = "logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Đăng Nhập",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LoginCard(
            navController = navController,
            loginViewModel = loginViewModel,
            nowRoute = nowRoute,
            nextRoute = nextRoute
        )

        Spacer(modifier = Modifier.height(35.dp))
        GoToSignUpButton {

        }

        Spacer(modifier = Modifier.height(50.dp))
        GoToAdminLoginButton {
            customerLoginViewModel.goToAdminLogin()
        }
    }
}

@Composable
private fun HandleGoToAdminLoginState(
    customerLoginViewModel: CustomerLoginViewModel,
    navController: NavController
) {
    val isGoToAdminLogin by customerLoginViewModel.isGoToAdminLogin.observeAsState()
    LaunchedEffect(key1 = isGoToAdminLogin) {
        when (isGoToAdminLogin) {
            true -> {
                navController.navigate(Route.AdminLogin.screen) {
                    popUpTo(Route.CustomerLogin.screen) { inclusive = true }
                }
            }

            false -> {}

            null -> {}
        }
    }
}

@Composable
private fun GoToSignUpButton(onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(40.dp)
    ) {
        Text("Đăng Ký", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
private fun GoToAdminLoginButton(onClick: () -> Unit) {

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
                append("Đăng nhập với quyền quản trị viên")
            }
        },
        modifier = Modifier.clickable { onClick() }
    )

}